package cmd

import (
	"context"
	"github.com/spf13/cobra"
)

var rootCmd = cobra.Command{
	Use:   "codegen",
	Short: "Code generator",
	RunE: func(cmd *cobra.Command, args []string) error {
		return container().Start(context.Background())
	},
}

func Execute() error {
	return rootCmd.Execute()
}
