plugins {
  alias(libs.plugins.kotlin)
  `java-gradle-plugin`
}

group = "org.codegen"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(projects.core)
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}
