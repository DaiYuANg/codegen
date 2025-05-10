plugins {
  application
  alias(libs.plugins.graalvm)
  alias(libs.plugins.jlink)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.shadow)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.ksp)
}

application{
  mainClass = "org.codegen.cli.CodegenCLIKt"
}

group = "org.codegen"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(enforcedPlatform(libs.koin.bom))
  ksp(enforcedPlatform(libs.koin.annotation.bom))
  implementation(enforcedPlatform(libs.koin.annotation.bom))
  implementation(libs.koin.core)
  implementation(libs.koin.logger.slf4j)
  implementation(libs.koin.annotation)
  ksp(libs.koin.ksp.compiler)
  implementation(libs.dotenv)

  implementation(libs.jansi)

  implementation(libs.clikt)
  implementation(libs.clikt.markdown)

  implementation(projects.core)
  implementation(libs.guava)
  implementation(libs.slf4j)
  implementation(libs.slf4jJulBridage)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.logback.core)
  implementation(libs.logback.classic)
  implementation(libs.jetbrains.annotation)
  implementation(libs.directories)
  implementation(libs.gestalt)
  implementation(libs.gestalt.toml)
  implementation(libs.gestalt.yaml)
  implementation(libs.gestalt.kotlin)
  implementation(libs.gestalt.json)
  implementation(libs.gestalt.git)
  implementation(libs.progressbar)
  implementation(libs.progressbar.ktx)
  implementation(libs.dotenv)
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  implementation(libs.kotlin.logging.jvm)
  testImplementation(kotlin("test"))
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

kotlin{
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}