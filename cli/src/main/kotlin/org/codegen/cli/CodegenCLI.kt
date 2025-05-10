package org.codegen.cli

import com.github.ajalt.clikt.core.main
import com.google.common.util.concurrent.AbstractIdleService
import com.google.common.util.concurrent.ServiceManager
import io.github.cdimascio.dotenv.Dotenv
import org.codegen.cli.command.RootCommand
import org.fusesource.jansi.AnsiConsole.systemInstall
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
@ComponentScan
class CliModule {

  @Single
  fun executor(): ExecutorService {
    return Executors.newThreadPerTaskExecutor(
      Thread.ofPlatform()
        .name("koma-cli-", 0)
        .factory()
    )
  }

  @Single
  fun serviceManager(services: List<AbstractIdleService>): ServiceManager {
    return ServiceManager(services)
  }

  @Single
  fun dotenv(): Dotenv {
    return Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().systemProperties().load()
  }
}

fun main(args: Array<String>) {
  val container = startKoin {
    printLogger()
    modules(CliModule().module)
  }
  val koin = container.koin
  systemInstall()
  val root = koin.get<RootCommand>()
  root.main(args)
}