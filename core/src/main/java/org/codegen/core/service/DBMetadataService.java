package org.codegen.core.service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.core.model.InputSource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import schemacrawler.schema.Column;
import schemacrawler.schemacrawler.*;
import schemacrawler.tools.utility.SchemaCrawlerUtility;
import us.fatehi.utility.LoggingConfig;
import us.fatehi.utility.datasource.DatabaseConnectionSource;
import us.fatehi.utility.datasource.DatabaseConnectionSources;
import us.fatehi.utility.datasource.MultiUseUserCredentials;

import java.util.List;
import java.util.logging.Level;

import static us.fatehi.utility.datasource.DatabaseConnectionSources.newDatabaseConnectionSource;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
@Singleton
public class DBMetadataService implements MetadataService {

  private final SchemaCrawlerOptions options;

  @Override
  public Table<String, String, List<Column>> collectMetadata(InputSource inputSource) {
    new LoggingConfig(Level.OFF);
    val dataSource = getDataSource(inputSource);
    val catalog = SchemaCrawlerUtility.getCatalog(dataSource, options);

    log.atInfo().log("schemas:{}", catalog.getSchemas());
    val schemaTableMap = HashBasedTable.<String, String, List<Column>>create();

    catalog.getSchemas().forEach(schema -> {
      val tables = catalog.getTables(schema);
      tables.forEach(table -> {
        log.atInfo().log("Table:{}", table.getName());
        schemaTableMap.put(schema.getFullName(), table.getName(), table.getColumns());
      });
    });

    log.atInfo().log("Metadata:{}", schemaTableMap);
    return schemaTableMap;
  }

  private @NotNull DatabaseConnectionSource getDataSource(@NotNull InputSource inputSource) {
    return newDatabaseConnectionSource(
      inputSource.url(),
      new MultiUseUserCredentials(
        inputSource.username(),
        inputSource.password()
      )
    );
  }
}
