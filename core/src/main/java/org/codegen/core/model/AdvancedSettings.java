package org.codegen.core.model;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record AdvancedSettings(
  NamingConventions namingConventions,
  LoggingSettings logging,
  List<Param> params
) {
  @RecordBuilder
  public record NamingConventions(
    String classPrefix,
    String fieldPrefix,
    String methodPrefix
  ) {
  }

  @RecordBuilder
  public record LoggingSettings(
    String level,
    String output
  ) {
  }

  @RecordBuilder
  public record Param(
    String key,
    String value,
    String description
  ) {
  }
}