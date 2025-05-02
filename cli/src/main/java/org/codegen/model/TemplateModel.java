package org.codegen.model;

public record TemplateModel(
  String name,
  String description,
  String templateFile,
  String outputPath
) {

}
