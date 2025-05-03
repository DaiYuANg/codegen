package org.codegen.core.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.codegen.core.model.TemplateConfiguration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@ToString
@Singleton
public class FreemarkerService implements TemplateService {
  private final Configuration configuration;

  private final TemplateConfiguration templateConfiguration;

  @SneakyThrows
  public void renderTemplate(String templateName, Map<String, Object> dataModel, Path outputFile) {
    val writer = Files.newBufferedWriter(outputFile);
    Template template = configuration.getTemplate(templateName);
    template.process(dataModel, writer);
  }
}
