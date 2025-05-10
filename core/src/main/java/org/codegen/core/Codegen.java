package org.codegen.core;

import io.avaje.inject.BeanScope;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.core.model.CodegenConfig;
import org.codegen.core.model.ConfigContext;
import org.codegen.core.service.MetadataService;
import org.codegen.core.service.TemplateService;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class Codegen {

  private final BeanScope beanScope = BeanScope.builder().build();

  private final ConfigContext configContext;

  @Builder
  public Codegen(@NotNull ConfigContext configContext) {
    this.configContext = configContext;
  }

  public void generate() {
    val metadataService = beanScope.get(MetadataService.class);
    val templateService = beanScope.get(TemplateService.class);
    val metadata = metadataService.collectMetadata(configContext.inputSource());
  }
}
