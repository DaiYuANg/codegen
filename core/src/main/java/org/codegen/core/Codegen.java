package org.codegen.core;

import io.avaje.inject.BeanScope;
import io.soabase.recordbuilder.core.RecordBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.core.model.CodegenConfig;
import org.codegen.core.model.InputSource;
import org.codegen.core.model.TemplateConfiguration;
import org.codegen.core.service.MetadataService;
import org.codegen.core.service.TemplateService;

@Slf4j
@RecordBuilder
public record Codegen(
  CodegenConfig codegenConfig,
  InputSource inputSource,
  TemplateConfiguration templateConfiguration
) {
  private static final BeanScope beanScope = BeanScope.builder().build();

  public void generate() {
    val metadataService = beanScope.get(MetadataService.class);
    val templateService = beanScope.get(TemplateService.class);
    val metadata = metadataService.collectMetadata(inputSource);
  }
}
