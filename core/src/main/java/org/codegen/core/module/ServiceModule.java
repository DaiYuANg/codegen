package org.codegen.core.module;

import com.google.inject.AbstractModule;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.codegen.core.provider.FreemarkProvider;
import org.codegen.core.service.DBMetadataService;
import org.codegen.core.service.FreemarkerService;
import org.codegen.core.service.MetadataService;
import org.codegen.core.service.TemplateService;

@Slf4j
public class ServiceModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(MetadataService.class).to(DBMetadataService.class);
    bind(TemplateService.class).to(FreemarkerService.class);
  }
}
