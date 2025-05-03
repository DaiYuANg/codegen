package org.codegen.cli.factory;

import com.google.inject.ConfigurationException;
import lombok.extern.slf4j.Slf4j;
import org.codegen.cli.context.DIContext;
import picocli.CommandLine;

@Slf4j
public class GuiceFactory implements CommandLine.IFactory {
  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return DIContext.INSTANCE.getInjector().getInstance(aClass);
    } catch (ConfigurationException ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(aClass); // fallback if missing
    }
  }
}
