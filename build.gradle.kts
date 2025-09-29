import io.miret.etienne.gradle.sass.CompileSass

plugins {
  java
  application
  id("org.beryx.jlink") version "3.1.3"
  id("com.gradleup.shadow") version "9.2.2"
  alias(libs.plugins.version.check)
  alias(libs.plugins.dotenv)
  alias(libs.plugins.lombok)
  alias(libs.plugins.spotless)
  alias(libs.plugins.git)
  alias(libs.plugins.graalvm.native)
  antlr
  id("io.freefair.github.dependency-manifest") version "8.14.2"
  id("com.coditory.manifest") version "1.1.0"
}

group = "org.codegen"
version = "1.0-SNAPSHOT"
val moduleName = "org.codegen"
repositories {
  mavenLocal()
  mavenCentral()
  google()
  maven("https://jitpack.io")
}

val junitVersion = "5.12.1"

java {
  modularity.inferModulePath.set(true)
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

application {
  mainModule.set("org.git.desk")
  mainClass.set("org.git.desk.Launcher")
}

val avajeInject = "11.6"
dependencies {
  implementation("dev.dirs:directories:26")
  implementation("ch.qos.logback:logback-classic:1.5.18")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
  implementation("org.slf4j:slf4j-api:2.0.17")
  implementation("io.avaje:avaje-inject:${avajeInject}")
  annotationProcessor("io.avaje:avaje-inject-generator:${avajeInject}")
  implementation("org.apache.commons:commons-lang3:3.18.0")
  implementation("commons-io:commons-io:2.20.0")
  implementation("com.google.guava:guava:33.4.8-jre")
  implementation(libs.jetbrains.annotation)
  testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
  implementation(libs.fastutil)
  implementation(libs.eclipse.collections.api)
  implementation(libs.eclipse.collections)
  implementation(libs.slf4jJulBridge)
  implementation(libs.mapstruct)
  annotationProcessor(libs.mapstruct.annotation.processor)
  implementation(libs.record.builder.core)
  annotationProcessor(libs.record.builder.processor)
  implementation(libs.h2)
  implementation(libs.mutiny)
}

tasks.withType<Test> {
  useJUnitPlatform()
}

lombok{
  lombokVersion.set("1.18.42")
}