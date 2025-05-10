package org.codegen.core.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record ConfigContext(
  CodegenConfig codegenConfig,
  InputSource inputSource,
  TemplateConfiguration templateConfiguration
) {
}
