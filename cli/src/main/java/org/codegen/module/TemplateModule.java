package org.codegen.module;

import com.google.inject.AbstractModule;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.codegen.provider.FreemarkProvider;
import org.codegen.service.FreemarkerService;
import org.codegen.service.TemplateService;

@Slf4j
public class TemplateModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Configuration.class).toProvider(new FreemarkProvider());
    bind(TemplateService.class).to(FreemarkerService.class);
  }
}
