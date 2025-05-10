plugins {
  alias(libs.plugins.version.check)
  alias(libs.plugins.dotenv)
  alias(libs.plugins.spotless)
  alias(libs.plugins.git)
}

group = "org.codegen"
version = "1.0-SNAPSHOT"

allprojects {
  repositories {
    maven("https://jitpack.io")
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
  }
}

