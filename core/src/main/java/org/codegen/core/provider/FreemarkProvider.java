package org.codegen.core.provider;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import jakarta.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.nio.charset.StandardCharsets;

@Slf4j
public class FreemarkProvider implements Provider<Configuration> {
  @Override
  public Configuration get() {
    val cfg = new Configuration(Configuration.VERSION_2_3_32);
    cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    cfg.setWrapUncheckedExceptions(true);
    return cfg;
  }
}
