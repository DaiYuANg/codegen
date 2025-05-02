package org.codegen.model;

import java.util.List;

public record TemplateConfiguration(
  TemplateModel entity,
  TemplateModel repository
) {
}