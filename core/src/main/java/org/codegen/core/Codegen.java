package org.codegen.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.core.model.CodegenConfig;
import org.codegen.core.model.InputSource;
import org.codegen.core.model.TemplateConfiguration;
import org.codegen.core.module.ConfigModule;
import org.codegen.core.module.ServiceModule;
import org.codegen.core.service.MetadataService;
import org.codegen.core.service.TemplateService;

@Slf4j
public class Codegen {

  private final Injector injector;

  @Builder
  public Codegen(CodegenConfig codegenConfig, InputSource inputSource, TemplateConfiguration templateConfiguration) {
    val configModule = ConfigModule.builder()
      .codegenConfig(codegenConfig)
      .inputSource(inputSource)
      .templateConfiguration(templateConfiguration)
      .build();

    this.injector = Guice.createInjector(configModule, new ServiceModule());
  }

  public void generate() {
    val metadataService = injector.getInstance(MetadataService.class);
    val templateService = injector.getInstance(TemplateService.class);
    val metadata = metadataService.collectMetadata();
  }
}
