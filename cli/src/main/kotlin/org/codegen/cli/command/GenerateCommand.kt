package org.codegen.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.defaultLazy
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import io.github.oshai.kotlinlogging.KotlinLogging
import org.codegen.cli.constant.CODEGEN
import org.codegen.cli.constant.ENV_PREFIX
import org.codegen.cli.constant.INPUT_SOURCE
import org.codegen.cli.constant.TEMPLATE
import org.codegen.cli.model.LoadedConfig
import org.codegen.core.CodegenBuilder
import org.codegen.core.model.CodegenConfig
import org.codegen.core.model.InputSource
import org.codegen.core.model.TemplateConfiguration
import org.fusesource.jansi.Ansi
import org.github.gestalt.config.builder.GestaltBuilder
import org.github.gestalt.config.loader.EnvironmentVarsLoader
import org.github.gestalt.config.loader.PropertyLoader
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder
import org.github.gestalt.config.source.FileConfigSourceBuilder
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder
import org.github.gestalt.config.toml.TomlLoader
import org.github.gestalt.config.yaml.YamlLoader
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import java.io.File

@Single
class GenerateCommand : CliktCommand() , KoinComponent {
  val configFile by option("-c", "--config", help = "config file")
    .file(mustExist = true, canBeDir = true, mustBeReadable = true, mustBeWritable = true)
    .help("config file")
    .defaultLazy { File("codegen.yaml") }
  private val log = KotlinLogging.logger {}
  override fun run() {
    log.atInfo { message = "config file:${configFile}" }
    echo(
      Ansi.ansi().fgBrightCyan().bold().render("generate code").reset(),
    )

    val (codegenConfig, inputSource, templateConfig) = loadConfig(configFile)

    val codeGen = CodegenBuilder.builder()
      .codegenConfig(codegenConfig)
      .inputSource(inputSource)
      .templateConfiguration(templateConfig)
      .build();

    codeGen.generate()
  }

  private fun loadConfig(configFile: File): LoadedConfig {
    val gestalt = GestaltBuilder()
      .addSource(EnvironmentConfigSourceBuilder.builder().setPrefix(ENV_PREFIX).build())
      .addSource(SystemPropertiesConfigSourceBuilder.builder().build())
      .addSource(FileConfigSourceBuilder.builder().setFile(configFile).build())
      .addConfigLoader(PropertyLoader())
      .addConfigLoader(TomlLoader())
      .addConfigLoader(EnvironmentVarsLoader())
      .addConfigLoader(YamlLoader())
      .setAnnotationTrimWhiteSpace(true)
      .build()

    gestalt.loadConfigs()

    return LoadedConfig(
      codegenConfig = gestalt.getConfig(CODEGEN, CodegenConfig::class.java),
      inputSource = gestalt.getConfig(INPUT_SOURCE, InputSource::class.java),
      templateConfig = gestalt.getConfig(TEMPLATE, TemplateConfiguration::class.java)
    )
  }
}