plugins {
  `java-library`
  alias(libs.plugins.lombok)
}

group = "org.codegen.core"

dependencies {
  implementation(libs.guice)
  implementation(libs.freemarker)
  implementation(libs.slf4j)
  implementation(libs.record.builder.core)
  annotationProcessor(libs.record.builder.processor)
  implementation(libs.jetbrains.annotation)
  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mysql)
  implementation(libs.schemacrawler.postgres)
  implementation(libs.schemacrawler.sqlserver)
  implementation(libs.mysql)
  implementation(libs.postgres)
  implementation(libs.sqlite)
  implementation(libs.guava)
}

tasks.test {
  useJUnitPlatform()
}