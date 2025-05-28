package cmd

import (
	"cli/internal/config"
	"go.uber.org/fx"
)

func container() *fx.App {
	return fx.New(config.Module)
}
