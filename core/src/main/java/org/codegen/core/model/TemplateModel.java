package org.codegen.core.model;

public record TemplateModel(
  String name,
  String description,
  String templateFile,
  String outputPath
) {

}
