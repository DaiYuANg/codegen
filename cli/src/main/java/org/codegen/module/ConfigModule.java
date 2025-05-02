package org.codegen.module;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;
import org.codegen.model.TemplateConfiguration;
import org.codegen.provider.TemplateConfigurationProvider;

@Slf4j
public class ConfigModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(TemplateConfiguration.class).toProvider(TemplateConfigurationProvider.class);
  }
}
