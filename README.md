# Criptografia

Este repositório implementa criptografia de forma transparente para a API e as camadas de serviço da aplicação. Ele garante que os campos sensíveis das entidades não fiquem visíveis diretamente, realizando a criptografia em tempo de execução ao converter a entidade para o banco de dados e vice-versa. Além disso, permite o uso de métodos CRUD nos campos sensíveis.

## Instalação

1. Clone o repositório:

```bash
git https://github.com/bispobr/Spring-java-criptografia.git
```

2. Instale as dependências com Maven

## Como usar

1. Inicie a aplicação com o Maven
2. A API está acessivem atraves do Link http://localhost:8080/dados-sensivel


## API Endpoints
A API contem os seguintes endpoints :

```http request
GET / - Retorna uma Lista com todos os Dados.
```

```http request
POST / - Registra um novo Dado.
Content-Type: application/json

{
  "usuarioDocumento": "xxxxxxxxxxxx",
  "creditoCardToken": "000000000000",
  "valor": 00000
}
```

```http request
DELETE /{id} - Exclui Um dado especifico.
```

## Banco-de-Dados
Esse projeto utiliza o H2 como Banco de Dados. Todas as migrations são gerenciadas atraves do Flyway.
