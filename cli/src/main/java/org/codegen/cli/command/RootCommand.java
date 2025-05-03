package org.codegen.cli.command;

import com.google.inject.Inject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.cli.context.DIContext;
import org.codegen.core.Codegen;
import org.codegen.core.model.CodegenConfig;
import org.codegen.core.model.InputSource;
import org.codegen.core.model.TemplateConfiguration;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.source.FileConfigSourceBuilder;
import picocli.CommandLine;

import java.io.File;

import static org.codegen.cli.constant.ConfigConstant.*;

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

  @Inject
  GestaltBuilder gestaltBuilder;

  public RootCommand() {
    DIContext.INSTANCE.getInjector().injectMembers(this);
  }

  @SneakyThrows
  @Override
  public void run() {
    log.atInfo().log("Config file {}", configFile);
    val gestalt = gestaltBuilder
      .addSource(FileConfigSourceBuilder.builder().setFile(configFile).build())
      .build();

    gestalt.loadConfigs();

    val codegenConfig = gestalt.getConfig(CODEGEN, CodegenConfig.class);
    val inputSourceConfig = gestalt.getConfig(INPUT_SOURCE, InputSource.class);
    val templateConfig = gestalt.getConfig(TEMPLATE, TemplateConfiguration.class);

    val codegen = Codegen.builder()
      .codegenConfig(codegenConfig)
      .inputSource(inputSourceConfig)
      .templateConfiguration(templateConfig)
      .build();

    codegen.generate();
  }
}
