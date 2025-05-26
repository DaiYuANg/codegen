package org.codegen.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class InitCommand : CliktCommand(), KoinComponent {
  override fun run() {
    TODO("Not yet implemented")
  }
}