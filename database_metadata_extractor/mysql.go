package database_metadata_extractor

import (
	"database/sql"
)

type MySQLExtractor struct {
	DB       *sql.DB
	Database string
}

func NewMySQLExtractor(db *sql.DB, database string) *MySQLExtractor {
	return &MySQLExtractor{DB: db, Database: database}
}

func (e *MySQLExtractor) GetTables() ([]*Table, error) {
	rows, err := e.DB.Query(`
		SELECT table_name, IFNULL(table_comment, '') 
		FROM information_schema.tables 
		WHERE table_schema = ?
	`, e.Database)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var tables []*Table
	for rows.Next() {
		var tableName, comment string
		if err := rows.Scan(&tableName, &comment); err != nil {
			return nil, err
		}

		columns, err := e.getColumns(tableName)
		if err != nil {
			return nil, err
		}
		tables = append(tables, &Table{Name: tableName, Comment: comment, Columns: columns})
	}
	return tables, nil
}

func (e *MySQLExtractor) getColumns(table string) ([]Column, error) {
	rows, err := e.DB.Query(`
		SELECT column_name, column_type, is_nullable, column_default, 
		       IF(column_key='PRI', TRUE, FALSE) as is_primary,
		       IFNULL(column_comment, '')
		FROM information_schema.columns
		WHERE table_schema = ? AND table_name = ?
		ORDER BY ordinal_position
	`, e.Database, table)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var columns []Column
	for rows.Next() {
		var col Column
		var nullable string
		if err := rows.Scan(&col.Name, &col.Type, &nullable, &col.Default, &col.IsPrimary, &col.Comment); err != nil {
			return nil, err
		}
		col.Nullable = nullable == "YES"
		columns = append(columns, col)
	}
	return columns, nil
}
