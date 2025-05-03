package org.codegen.core.service;

import com.google.common.collect.Table;
import schemacrawler.schema.Column;

import java.util.List;

public interface MetadataService {
  Table<String, String, List<Column>> collectMetadata();
}
