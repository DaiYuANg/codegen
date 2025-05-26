package org.codegen.cli.command

import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintHelpMessage
import com.github.ajalt.clikt.core.subcommands
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
import org.koin.core.component.inject
import java.io.File

@Single
class RootCommand : CliktCommand(), KoinComponent {
  private val log = KotlinLogging.logger {}

  private val generateCommand: GenerateCommand by inject()
  private val initCommand: InitCommand by inject()

  init {
    subcommands(generateCommand, initCommand)
  }

  init {
    completionOption()
  }

  override fun run() {
    log.atInfo { message = "codegen" }
    throw PrintHelpMessage(currentContext)
  }
}