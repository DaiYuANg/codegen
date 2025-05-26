package org.codegen.core.service;

import io.avaje.inject.test.InjectTest;
import jakarta.inject.Inject;
import lombok.val;
import org.codegen.core.model.InputSource;
import org.codegen.core.model.InputSourceBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@InjectTest
public class DBMetadataServiceTest {

  @Inject
  public MetadataService metadataService;

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
    "postgres:17-alpine"
  );

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @BeforeEach
  void setUp() {
  }

  @Test
  void collectMetadata() {
    val source = InputSourceBuilder.builder()
      .name("test postgres")
      .type("database")
      .url(postgres.getJdbcUrl())
      .username(postgres.getUsername())
      .password(postgres.getPassword())
      .build();
    val result = metadataService.collectMetadata(source);
    System.err.println(result);
  }
}