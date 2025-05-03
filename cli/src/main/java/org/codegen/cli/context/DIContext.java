package org.codegen.cli.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codegen.cli.module.GestaltModule;

@Slf4j
@Getter
public enum DIContext {

  INSTANCE;

  private final Injector injector = Guice.createInjector(new GestaltModule());
}
