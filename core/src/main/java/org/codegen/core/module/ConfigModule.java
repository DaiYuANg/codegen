package org.codegen.core.module;

import com.google.inject.AbstractModule;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.codegen.core.model.CodegenConfig;
import org.codegen.core.model.InputSource;
import org.codegen.core.model.TemplateConfiguration;

@Slf4j
@Builder
public class ConfigModule extends AbstractModule {

  private final CodegenConfig codegenConfig;

  private final InputSource inputSource;

  private final TemplateConfiguration templateConfiguration;


  @Override
  protected void configure() {
    bind(CodegenConfig.class).toInstance(codegenConfig);
    bind(InputSource.class).toInstance(inputSource);
    bind(TemplateConfiguration.class).toInstance(templateConfiguration);
  }
}
