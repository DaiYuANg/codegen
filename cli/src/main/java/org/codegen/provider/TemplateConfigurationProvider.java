package org.codegen.provider;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codegen.model.TemplateConfiguration;
import org.github.gestalt.config.Gestalt;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
public class TemplateConfigurationProvider implements Provider<TemplateConfiguration> {

  private final Gestalt gestalt;

  @SneakyThrows
  @Override
  public TemplateConfiguration get() {
    return gestalt.getConfig("templates",  TemplateConfiguration.class);
  }
}
