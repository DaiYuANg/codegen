package generator

import (
	"bytes"
	"github.com/Masterminds/sprig/v3"
	"path/filepath"
	"text/template"
)

type Generator struct {
	//TmplEngine TemplateEngine // 接口，支持 quicktemplate 或 text/template
	OutputDir    string
	TypeMap      map[string]string
	templatesDir string
}

func NewGenerator(templatesDir string) *Generator {
	return &Generator{
		templatesDir: templatesDir,
	}
}

func (g *Generator) Render(lang, templateName string, data interface{}) (string, error) {
	tmplPath := filepath.Join(g.templatesDir, lang, templateName+".gotmpl")

	tmpl, err := template.New(templateName).Funcs(sprig.TxtFuncMap()).ParseFiles(tmplPath)
	if err != nil {
		return "", err
	}

	var buf bytes.Buffer
	if err := tmpl.Execute(&buf, data); err != nil {
		return "", err
	}
	return buf.String(), nil
}
