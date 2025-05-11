package org.codegen.cli.model

import org.codegen.core.model.CodegenConfig
import org.codegen.core.model.InputSource
import org.codegen.core.model.TemplateConfiguration

data class LoadedConfig(
  val codegenConfig: CodegenConfig,
  val inputSource: InputSource,
  val templateConfig: TemplateConfiguration
)