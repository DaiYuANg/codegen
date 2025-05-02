<#--
    entity_with_lombok.ftl - FreeMarker template to generate a Spring Boot JPA Entity class with Lombok annotations.
    This template expects the following parameters:
    - tableName: the name of the database table
    - className: the name of the entity class
    - package: the package of the entity class
    - columns: a list of columns for the entity
    - idColumn: the column used for the @Id
    - generatedValueStrategy: strategy for generating the primary key (e.g., AUTO, IDENTITY)
-->

package ${package}.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "${tableName}")
@Data  <#-- Lombok's @Data generates getter, setter, toString, equals, and hashCode methods -->
@NoArgsConstructor  <#-- Lombok's @NoArgsConstructor generates a no-args constructor -->
public class ${className} implements Serializable {

<#-- Loop through columns to create fields -->
<#list columns as column>
  @Column(name = "${column.name}")
  private ${column.type} ${column.name};
</#list>

@Id
@GeneratedValue(strategy = GenerationType.${generatedValueStrategy})
@Column(name = "${idColumn.name}")
private Long id;

}
