package org.codegen.core.model;

import java.util.List;

public record InputSource(
    String type,
    String name,
    String url,
    String username,
    String password,
    List<String> tables
//    String path
) {
}