package org.codegen.core.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.val;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Factory
public class ExecutorFactory {

  @Bean
  Executor executor() {
    val factory = Thread.ofVirtual().name("io-worker-", 0).factory();
    return Executors.newThreadPerTaskExecutor(factory);
  }
}
