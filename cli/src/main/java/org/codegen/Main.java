package org.codegen;

import io.github.cdimascio.dotenv.Dotenv;
import org.codegen.command.RootCommand;
import picocli.CommandLine;

public class Main {

  static {
    Dotenv.configure()
      .systemProperties()
      .ignoreIfMissing()
      .load();
  }

  public static void main(String[] args) {
    new CommandLine(new RootCommand()).execute(args);
  }
}