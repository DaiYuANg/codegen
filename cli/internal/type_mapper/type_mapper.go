package type_mapper

import (
	"strings"
)

type TypeMapper struct {
	mapping map[string]string
}

func NewTypeMapper() *TypeMapper {
	return &TypeMapper{
		mapping: map[string]string{
			// MySQL & SQLite 常见类型映射到 Java
			"int":       "Integer",
			"integer":   "Integer",
			"smallint":  "Short",
			"bigint":    "Long",
			"varchar":   "String",
			"char":      "String",
			"text":      "String",
			"datetime":  "java.time.LocalDateTime",
			"timestamp": "java.time.LocalDateTime",
			"date":      "java.time.LocalDate",
			"float":     "Float",
			"double":    "Double",
			"decimal":   "java.math.BigDecimal",
			"boolean":   "Boolean",
			"blob":      "byte[]",
		},
	}
}

// 简单根据字段类型名匹配类型，忽略大小写，包含判断
func (tm *TypeMapper) ToJavaType(dbType string) string {
	dbType = strings.ToLower(dbType)
	for k, v := range tm.mapping {
		if strings.Contains(dbType, k) {
			return v
		}
	}
	// 默认用 String
	return "String"
}

func (tm *TypeMapper) ToTypeScriptType(dbType string) string {
	dbType = strings.ToLower(dbType)
	switch {
	case strings.Contains(dbType, "int"):
		return "number"
	case strings.Contains(dbType, "float"), strings.Contains(dbType, "double"), strings.Contains(dbType, "decimal"):
		return "number"
	case strings.Contains(dbType, "bool"):
		return "boolean"
	case strings.Contains(dbType, "date"), strings.Contains(dbType, "time"), strings.Contains(dbType, "timestamp"):
		return "Date"
	case strings.Contains(dbType, "blob"), strings.Contains(dbType, "binary"):
		return "Uint8Array"
	default:
		return "string"
	}
}
