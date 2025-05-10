package org.codegen.core.service;

import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class ASTService {

  private final JavaParser javaParser;

  @SneakyThrows
  public void parse(File sourceFile) {
    val parseResult = javaParser.parse(sourceFile);
    parseResult.ifSuccessful(compilationUnit -> {
      compilationUnit.findAll(ClassOrInterfaceDeclaration.class)
        .forEach(cls -> {
          if (cls.isAnnotationPresent("Entity")) {
            System.out.println("Found JPA Entity: " + cls.getNameAsString());

            // 提取表名（可选）
            cls.getAnnotationByName("Table").ifPresent(annotation -> {
              System.out.println("  Table Annotation: " + annotation);
            });

            // 遍历字段
            cls.getFields().forEach(field -> {
              String fieldName = field.getVariable(0).getNameAsString();
              String fieldType = field.getVariable(0).getType().toString();
              System.out.println("  Field: " + fieldName + " : " + fieldType);

              // 提取注解
              field.getAnnotations().forEach(ann -> {
                System.out.println("    Annotation: " + ann.getNameAsString());
                ann.ifNormalAnnotationExpr(normal -> {
                  System.out.println("      Details: " + normal.getPairs());
                });
              });
            });
          }
        });
    });

  }
}
