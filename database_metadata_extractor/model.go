package database_metadata_extractor

type Table struct {
	Name        string
	Comment     string
	Columns     []Column
	PrimaryKeys []string
	Indexes     []Index
}

type Column struct {
	Name      string
	Type      string
	Nullable  bool
	Default   *string
	IsPrimary bool
	Comment   string
}

type Index struct {
	Name    string
	Columns []string
	Unique  bool
}
