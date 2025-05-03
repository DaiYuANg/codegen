pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
    maven { setUrl("https://jitpack.io") }
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.22"
}

buildCache {
  local {
    isEnabled = true
    directory = File(rootProject.projectDir, ".gradle/build-cache")
  }
}

rootProject.name = "codegen"
include("cli")
include("document")
include("core")