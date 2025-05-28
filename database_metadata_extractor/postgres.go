package database_metadata_extractor

import (
	"database/sql"
	"strings"
)

type PostgresExtractor struct {
	db *sql.DB
}

func (e *PostgresExtractor) GetTables() ([]*Table, error) {
	rows, err := e.db.Query(`
		SELECT table_name
		FROM information_schema.tables
		WHERE table_schema = 'public' AND table_type = 'BASE TABLE'
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

		table := &Table{
			Name:        tableName,
			Columns:     e.getColumns(tableName),
			PrimaryKeys: e.getPrimaryKeys(tableName),
			Indexes:     e.getIndexes(tableName),
		}
		tables = append(tables, table)
	}
	return tables, nil
}

func (e *PostgresExtractor) getColumns(table string) []Column {
	rows, err := e.db.Query(`
		SELECT column_name, data_type, is_nullable, column_default
		FROM information_schema.columns
		WHERE table_name = $1`, table)
	if err != nil {
		return nil
	}
	defer rows.Close()

	var cols []Column
	for rows.Next() {
		var col Column
		var nullable string
		err := rows.Scan(&col.Name, &col.Type, &nullable, &col.Default)
		if err != nil {
			continue
		}
		col.Nullable = nullable == "YES"
		cols = append(cols, col)
	}
	return cols
}

func (e *PostgresExtractor) getPrimaryKeys(table string) []string {
	query := `
		SELECT a.attname
		FROM pg_index i
		JOIN pg_attribute a ON a.attrelid = i.indrelid AND a.attnum = ANY(i.indkey)
		WHERE i.indrelid = $1::regclass AND i.indisprimary;
	`
	rows, err := e.db.Query(query, table)
	if err != nil {
		return nil
	}
	defer rows.Close()

	var keys []string
	for rows.Next() {
		var col string
		rows.Scan(&col)
		keys = append(keys, col)
	}
	return keys
}

func (e *PostgresExtractor) getIndexes(table string) []Index {
	query := `
		SELECT
			i.relname AS index_name,
			ix.indisunique,
			string_agg(a.attname, ',') AS columns
		FROM pg_class t, pg_class i, pg_index ix, pg_attribute a
		WHERE
			t.oid = ix.indrelid AND
			i.oid = ix.indexrelid AND
			a.attrelid = t.oid AND
			a.attnum = ANY(ix.indkey) AND
			t.relkind = 'r' AND
			t.relname = $1
		GROUP BY i.relname, ix.indisunique;
	`
	rows, err := e.db.Query(query, table)
	if err != nil {
		return nil
	}
	defer rows.Close()

	var indexes []Index
	for rows.Next() {
		var name, colsStr string
		var unique bool
		rows.Scan(&name, &unique, &colsStr)
		indexes = append(indexes, Index{
			Name:    name,
			Unique:  unique,
			Columns: strings.Split(colsStr, ","),
		})
	}
	return indexes
}
