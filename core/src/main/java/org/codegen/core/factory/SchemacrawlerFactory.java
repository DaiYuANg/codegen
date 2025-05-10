package org.codegen.core.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;
import schemacrawler.schemacrawler.*;

@Factory
@Slf4j
public class SchemacrawlerFactory {
  @Bean
  LimitOptionsBuilder limitOptionsBuilder() {
    return LimitOptionsBuilder.builder()
      .includeTables(tableFullName -> !tableFullName.contains("_PK"));
  }

  @Bean
  LoadOptionsBuilder loadOptionsBuilder() {
    return LoadOptionsBuilder.builder()
      .withSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
  }

  @Bean
  SchemaCrawlerOptions schemaCrawlerOptions(
    LimitOptionsBuilder limitOptionsBuilder,
    LoadOptionsBuilder loadOptionsBuilder
  ) {
    return SchemaCrawlerOptionsBuilder.newSchemaCrawlerOptions()
      .withLimitOptions(limitOptionsBuilder.toOptions())
      .withLoadOptions(loadOptionsBuilder.toOptions());
  }
}
