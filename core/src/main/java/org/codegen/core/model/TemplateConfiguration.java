package org.codegen.core.model;


public record TemplateConfiguration(
  TemplateModel entity,
  TemplateModel repository
) {
}