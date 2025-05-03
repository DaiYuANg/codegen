plugins {
  java
  application
  alias(libs.plugins.lombok)
  alias(libs.plugins.plantuml)
  alias(libs.plugins.graalvm)
  alias(libs.plugins.jlink)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.shadow)
}

application{
  mainClass = "org.codegen.Main"
  mainModule = "codegen"
}


group = "org.codegen"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(projects.core)
  implementation(libs.picocli)
  annotationProcessor(libs.picocli.codegen)
  implementation(libs.guice)
  implementation(libs.guava)
  implementation(libs.slf4j)
  implementation(libs.slf4jJulBridage)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.logback)
  implementation(libs.jetbrains.annotation)
  implementation(libs.directories)
  implementation(libs.record.builder.core)
  annotationProcessor(libs.record.builder.processor)
  implementation(libs.javapoet)
  implementation(libs.gestalt)
  implementation(libs.gestalt.guice)
  implementation(libs.gestalt.toml)
  implementation(libs.gestalt.yaml)
  implementation(libs.dotenv)
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
  useJUnitPlatform()
}

graalvmNative {
  binaries {
    named("main") {
      imageName.set("application")
      mainClass.set("org.codegen.Main")
      debug.set(true)
      verbose.set(true)
      fallback.set(true)
      sharedLibrary.set(false)
      richOutput.set(false)
      quickBuild.set(false)

      buildArgs.add("--link-at-build-time")
      useFatJar.set(true)
    }
  }
}