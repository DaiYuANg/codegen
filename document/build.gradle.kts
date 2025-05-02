plugins {
  alias(libs.plugins.mkdocs)
}

mkdocs {
  sourcesDir = "src"
  strict = true
}