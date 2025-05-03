package org.codegen.core.model;

import java.util.List;

public record AdvancedSettings(
  NamingConventions namingConventions,
  LoggingSettings logging,
  List<Param> params
) {
  public record NamingConventions(
    String classPrefix,
    String fieldPrefix,
    String methodPrefix
  ) {
  }

  public record LoggingSettings(
    String level,
    String output
  ) {
  }

  public record Param(
    String key,
    String value,
    String description
  ) {
  }
}