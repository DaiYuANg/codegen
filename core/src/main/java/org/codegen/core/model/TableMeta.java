package org.codegen.core.model;

import io.soabase.recordbuilder.core.RecordBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RecordBuilder
@Slf4j
public record TableMeta(
  String name,
  String className,
  List<ColumnMeta> columns
) {
}
