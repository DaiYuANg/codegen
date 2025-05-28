package database_metadata_extractor

import (
	"database/sql"
	"fmt"
)

type Extractor interface {
	GetTables() ([]*Table, error)
}

func NewExtractor(driverName string, db *sql.DB) (Extractor, error) {
	switch driverName {
	case "postgres":
		return &PostgresExtractor{db: db}, nil
	default:
		return nil, fmt.Errorf("unsupported driver: %s", driverName)
	}
}
