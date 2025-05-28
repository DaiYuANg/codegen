package database_metadata_extractor

import (
	"database/sql"
	"fmt"
)

type SQLiteExtractor struct {
	DB *sql.DB
}

func NewSQLiteExtractor(db *sql.DB) *SQLiteExtractor {
	return &SQLiteExtractor{DB: db}
}

func (e *SQLiteExtractor) GetTables() ([]*Table, error) {
	rows, err := e.DB.Query(`
		SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'
	`)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var tables []*Table
	for rows.Next() {
		var tableName string
		if err := rows.Scan(&tableName); err != nil {
			return nil, err
		}
		columns, err := e.getColumns(tableName)
		if err != nil {
			return nil, err
		}
		tables = append(tables, &Table{Name: tableName, Columns: columns})
	}
	return tables, nil
}

func (e *SQLiteExtractor) getColumns(table string) ([]Column, error) {
	rows, err := e.DB.Query(fmt.Sprintf(`PRAGMA table_info(%s)`, table))
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var columns []Column
	for rows.Next() {
		var col Column
		var cid int
		var notnull int
		var dfltValue sql.NullString
		var pk int
		if err := rows.Scan(&cid, &col.Name, &col.Type, &notnull, &dfltValue, &pk); err != nil {
			return nil, err
		}
		col.Nullable = notnull == 0
		if dfltValue.Valid {
			col.Default = &dfltValue.String
		}
		col.IsPrimary = pk != 0
		col.Comment = "" // SQLite 没有字段注释
		columns = append(columns, col)
	}
	return columns, nil
}
