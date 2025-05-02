package org.codegen.core;

import com.google.inject.Injector;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.service.TemplateService;

@Slf4j
@Builder
public class Codegen {

  private final Injector injector;

  public void generate(){
    val templateService = injector.getInstance(TemplateService.class);
    System.err.println(templateService.templateConfiguration());
  }
}
