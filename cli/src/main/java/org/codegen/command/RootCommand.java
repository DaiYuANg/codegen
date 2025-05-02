package org.codegen.command;

import com.google.inject.Guice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.core.Codegen;
import org.codegen.model.CodegenConfig;
import org.codegen.module.ConfigModule;
import org.codegen.module.TemplateModule;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.guice.GestaltModule;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.FileConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlLoader;
import org.github.gestalt.config.yaml.YamlLoader;
import picocli.CommandLine;

import java.io.File;

import static org.codegen.constant.ConfigConstant.CODEGEN;

@CommandLine.Command(
  name = "codegen",
  mixinStandardHelpOptions = true
)
@Slf4j
public class RootCommand implements Runnable {
  @CommandLine.Option(
    names = {"-c", "--config"},
    description = "config file",
    required = true,
    showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
    defaultValue = "codegen.toml"
  )
  File configFile;

  @CommandLine.Spec
  CommandLine.Model.CommandSpec spec;

  @SneakyThrows
  @Override
  public void run() {
    log.atInfo().log("Config file {}", configFile);
    val gestalt = new GestaltBuilder()
      .addSource(EnvironmentConfigSourceBuilder.builder().setPrefix("CODEGEN_").build())
      .addSource(SystemPropertiesConfigSourceBuilder.builder().build())
      .addSource(FileConfigSourceBuilder.builder().setFile(configFile).build())
      .addConfigLoader(new PropertyLoader())
      .addConfigLoader(new TomlLoader())
      .addConfigLoader(new EnvironmentVarsLoader())
      .addConfigLoader(new YamlLoader())
      .setAnnotationTrimWhiteSpace(true)
      .build();

    gestalt.loadConfigs();

    val gestaltModule = new GestaltModule(gestalt);
    val freemarkerModule = new TemplateModule();
    val configMoulde = new ConfigModule();
    val injector = Guice.createInjector(gestaltModule, freemarkerModule, configMoulde);
    val codegen = Codegen.builder()
      .injector(injector)
      .build();

    codegen.generate();

    log.atInfo().log(gestalt.debugPrint());
    val codegenConfig = gestalt.getConfig(CODEGEN, CodegenConfig.class);
    log.atInfo().log("CodegenConfig :{}", codegenConfig);
    spec.commandLine().usage(System.out);
  }
}
