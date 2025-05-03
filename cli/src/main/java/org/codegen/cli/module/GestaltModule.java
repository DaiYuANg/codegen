package org.codegen.cli.module;

import com.google.inject.AbstractModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codegen.cli.provider.GestaltBuilderProvider;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.FileConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlLoader;
import org.github.gestalt.config.yaml.YamlLoader;

import static org.codegen.cli.constant.ConfigConstant.ENV_PREFIX;

@Slf4j
public class GestaltModule extends AbstractModule {

  @SneakyThrows
  @Override
  protected void configure() {
    bind(GestaltBuilder.class).toProvider(GestaltBuilderProvider.class);
  }
}
