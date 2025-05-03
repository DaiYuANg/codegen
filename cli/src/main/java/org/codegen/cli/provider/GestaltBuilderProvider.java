package org.codegen.cli.provider;

import jakarta.inject.Provider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlLoader;
import org.github.gestalt.config.yaml.YamlLoader;

import static org.codegen.cli.constant.ConfigConstant.ENV_PREFIX;

@Slf4j
public class GestaltBuilderProvider implements Provider<GestaltBuilder> {
  @SneakyThrows
  @Override
  public GestaltBuilder get() {
    return new GestaltBuilder()
      .addSource(EnvironmentConfigSourceBuilder.builder().setPrefix(ENV_PREFIX).build())
      .addSource(SystemPropertiesConfigSourceBuilder.builder().build())
      .addConfigLoader(new PropertyLoader())
      .addConfigLoader(new TomlLoader())
      .addConfigLoader(new EnvironmentVarsLoader())
      .addConfigLoader(new YamlLoader())
      .setAnnotationTrimWhiteSpace(true);
  }
}
