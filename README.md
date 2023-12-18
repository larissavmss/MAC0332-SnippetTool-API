# SnippetTool

O Snippet Tool é um sistema que tem como objetivo auxiliar desenvolvedores a criar, armazenar e gerenciar seus snippets de forma simples e eficiente.

## Como executar

### Aplicação Spring
1. É preciso primeiramente configurar o application.properties com suas variáveis locais;
2. Com sua aplicação configurada, rode o script localizado no src/main/resources/sql/snippettool_v0.sql no seu banco de dados Postgresql;
3. Garanta que sua IDE possui as dependências que o projeto utiliza;
4. Rode o projeto SnippettoolApplication.java como Java Application;
5. Se você seguiu corretamente os passos anteriores, sua aplicação irá começar a rodar. Para testar os endpoints criados, há uma coleção postman com as requisições em postman_collection.json, basta usar ela alterando as variáveis do body ou path.

### Aplicação React

Além desse repositório, para usar a interface gráfica do sistema é necessário clonar também o repositório do [front](https://github.com/mac0332/SnippetTool-Front)

1. Dentro do repositório rode npm install;
2. Para rodar aplicação execute npm start;
3. As rotas disponíveis são:
   - localhost:3000/tags -> Crie e gerencie suas tags
   - localhost:3000/folders -> Crie e gerencie seus diretórios
   - localhost:3000/ -> Faça login ou registre-se para visualizar, buscar, criar e editar seus snippets


## Desenvolvedores:

- Larissa Vitória Medeiros Silva
- Kaique Nunes de Oliveira
- Luiz Gabriel Lima Arrais
- Cássio Azevedo Cancio
- Nathan de Oliveira Nunes

## Issues:

As issues desse projeto estão no [Trello](https://trello.com/b/3o8LKGsy/snippet-tool)
