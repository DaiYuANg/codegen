package org.codegen.model;

public record CodegenConfig(
  String name,
  String version,
  String description,
  String outputDirectory,
  boolean overwriteExisting
//  List<InputSource> inputSources,
//  List<Template> templates,
//  AdvancedSettings advanced
) {

}
