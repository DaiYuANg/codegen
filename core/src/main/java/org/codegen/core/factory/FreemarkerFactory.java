package org.codegen.core.factory;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.nio.charset.StandardCharsets;

@Factory
@Slf4j
public class FreemarkerFactory {
  @Bean
  public Configuration get() {
    val cfg = new Configuration(Configuration.VERSION_2_3_32);
    cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    cfg.setWrapUncheckedExceptions(true);
    return cfg;
  }
}
