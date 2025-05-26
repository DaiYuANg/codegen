package org.codegen.core.model;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record InputSource(
  String type,
  String name,
  String url,
  String username,
  String password,
  List<String> tables
//    String path
) {
}