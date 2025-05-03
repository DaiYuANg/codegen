package org.codegen.core.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record ColumnMeta(
  String fieldName,
  String columnName,
  String type,
  boolean isPrimaryKey,
  boolean isNullable,
  boolean isRelation
) {
}
