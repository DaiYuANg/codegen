package org.codegen.core.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record TemplateModel(
  String name,
  String description,
  String templateFile,
  String outputPath
) {

}
