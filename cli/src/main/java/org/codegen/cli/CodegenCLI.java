package org.codegen.cli;

import io.github.cdimascio.dotenv.Dotenv;
import org.codegen.cli.command.RootCommand;
import org.codegen.cli.factory.GuiceFactory;
import picocli.CommandLine;

public class CodegenCLI {

  static {
    Dotenv.configure()
      .systemProperties()
      .ignoreIfMissing()
      .load();
  }

  public static void main(String[] args) {
    new CommandLine(new RootCommand(),new GuiceFactory()).execute(args);
  }
}