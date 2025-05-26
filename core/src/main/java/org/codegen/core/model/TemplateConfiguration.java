package org.codegen.core.model;


import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record TemplateConfiguration(
  TemplateModel entity,
  TemplateModel repository
) {
}