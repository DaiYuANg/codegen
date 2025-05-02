package org.codegen.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.codegen.model.TemplateConfiguration;
import org.github.gestalt.config.guice.InjectConfig;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@ToString
public class FreemarkerService implements TemplateService {
  private final Configuration configuration;

  private final TemplateConfiguration templateConfiguration;

  @SneakyThrows
  public void renderTemplate(String templateName, Map<String, Object> dataModel, Path outputFile) {
    Writer writer = Files.newBufferedWriter(outputFile);
    Template template = configuration.getTemplate(templateName);
    template.process(dataModel, writer);
  }

  @Override
  public TemplateConfiguration templateConfiguration() {
    return templateConfiguration;
  }
}
