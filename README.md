# Projeto: API com Criptografia Transparente e Monitoramento

## Descrição

Esta API implementa criptografia de forma transparente para os serviços e camadas de persistência, garantindo que campos sensíveis das entidades sejam protegidos em tempo de execução. A criptografia é aplicada automaticamente durante a conversão de entidades para o banco de dados e na leitura dos dados persistidos, evitando a exposição direta de informações sensíveis.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para construção da API.
- **Lombok (@Slf4j)**: Utilizado para geração  de logs.
- **Swagger (Springdoc OpenAPI)**: Documentação interativa da API.
- **Spring Boot Actuator**: Monitoramento da aplicação (health checks, métricas.).
- **Integração Swagger + Actuator**: Permite exposição e monitoramento via interface Swagger.
- **H2 Database**: Banco de dados em memória utilizado para testes e desenvolvimento.
- **Flyway**: Gerenciamento de versionamento e migração do banco de dados.
- **Cache**: uso de cache local
- **Tratamento de Exceções** - @RestControllerAdvice
- **JUnit 5 + Mockito** – Testes Unitarios
- **Docker** – criação, implantação e gerenciamento de aplicações dentro de contêineres.

## Requisitos

- Java 21
- Maven 


## Executando o Projeto

1. Clone o repositório:

```bash
git https://github.com/bispobr/Spring-java-criptografia.git
```


## Como usar

1. Inicie a aplicação 
2. A API está acessivel atraves do endereço http://localhost:8080/dados-sensivel
3. A documentação da API está acessível através do Link http://localhost:8080/swagger-ui/index.html#/
4. O endpoint de saúde e métricas do Actuator está acessível através do Link http://localhost:8080/actuator/health

## Como Rodar em um Container (Opcional)

1. Construa o projeto

```bash
mvn clean package 
```

2. Gere a Imagem Docker, com o Docker  instalado execute:


```bash
docker build -t criptografia . 
```

3. Execute o Container

```bash
docker run -p 8080:8080 criptografia
```


## API Endpoints
A API contem os seguintes endpoints:

```http request
POST /dados-sensivel - Inserção de novos registros no banco de dados.
Content-Type: application/json

{
  "usuarioDocumento": "xxxxxxxxxxxx",
  "creditoCardToken": "000000000000",
  "valor": 00000
}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `usuarioDocumento` | `String` | **Obrigatório**. O nome do produto 
| `creditoCardToken` | `String` | **Obrigatório**. Dados do cartão 
| `valor` | `Long` | **Obrigatório**. Valor cartão 

```http request
PUT /dados-sensivel/{id} - Atualização de registros no banco de dados.
Content-Type: application/json

{
  "usuarioDocumento": "xxxxxxxxxxxx",
  "creditoCardToken": "000000000000",
  "valor": 00000
}
```

```http request
GET /dados-sensivel - Consulta de todos os registros salvos
```

```http request
GET /dados-sensivel/{id} - Consulta de todos os registros salvos
```

```http request
DELETE /dados-sensivel/{id} - Exclusão de registros do banco de dados
```

