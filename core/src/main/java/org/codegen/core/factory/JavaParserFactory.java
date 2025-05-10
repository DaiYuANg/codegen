package org.codegen.core.factory;

import com.github.javaparser.JavaParser;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;

@Factory
@Slf4j
public class JavaParserFactory {

  @Bean
  public JavaParser javaParser(){
    return new JavaParser();
  }
}
