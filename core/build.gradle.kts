plugins {
  `java-library`
  alias(libs.plugins.lombok)
  alias(libs.plugins.plantuml)
//  antlr
}

group = "org.codegen.core"

dependencies {
  implementation(libs.avaje.inject)
  testImplementation(libs.avaje.inject.test)
  annotationProcessor(libs.avaje.inject.generator)
  implementation(libs.freemarker)
  implementation(libs.velocity)
  implementation(libs.slf4j)
  implementation(libs.record.builder.core)
  annotationProcessor(libs.record.builder.processor)
  implementation(libs.jetbrains.annotation)
  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mysql)
  implementation(libs.schemacrawler.postgres)
  implementation(libs.schemacrawler.sqlserver)
  implementation(libs.vavr)
  implementation(libs.mysql)
  implementation(libs.postgres)
  implementation(libs.sqlite)
  implementation(libs.guava)
  implementation(libs.javapoet)

  implementation(libs.javaparser.core)
//  antlr(libs.antlr)

  implementation(libs.jackson.core)
  implementation(libs.jackson.annotations)
  implementation(libs.jackson.databind)
  implementation(libs.jackson.dataformat.yaml)
  implementation(libs.jackson.dataformat.toml)
  testImplementation(enforcedPlatform(libs.testcontainers))
  testImplementation(libs.testcontainers.mysql)
  testImplementation(libs.testcontainers.postgresql)

  testImplementation(enforcedPlatform(libs.junit.bom))
  testImplementation(libs.junit.api)
  testImplementation(libs.junit.engine)
  testImplementation(libs.junit.juiter)
}

tasks.test {
  useJUnitPlatform()
}