# Spring Java - Criptografia

API REST desenvolvida com Java e Spring Boot para trabalhar com dados sensíveis, aplicando criptografia durante a persistência das informações.

## Funcionalidades

* Cadastro de dados sensíveis
* Consulta de dados sensíveis por identificador
* Atualização de dados sensíveis
* Exclusão de dados sensíveis
* Criptografia de informações sensíveis
* Documentação da API com Swagger/OpenAPI
* Monitoramento com Spring Boot Actuator
* Métricas da aplicação

## Tecnologias

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Spring Boot Actuator
* Springdoc OpenAPI
* Maven
* Docker

## Requisitos

* Java 21
* Maven
* Docker (opcional)

## Configuração

A aplicação utiliza H2 Database em memória para persistência durante a execução.

Principais configurações:

| Propriedade                                 | Descrição                               |
| ------------------------------------------- | --------------------------------------- |
| `spring.datasource.url`                     | URL do banco H2 em memória              |
| `spring.datasource.username`                | Usuário utilizado para conexão com o H2 |
| `spring.datasource.password`                | Senha utilizada para conexão com o H2   |
| `spring.h2.console.enable`                  | Habilita o console do H2                |
| `spring.h2.console.path`                    | Caminho do console do H2                |
| `spring.jpa.hibernate.ddl-auto`             | Estratégia de atualização do schema     |
| `management.endpoints.web.exposure.include` | Endpoints do Actuator expostos          |

## Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/bispobr/Spring-java-criptografia.git
cd Spring-java-criptografia
```

Execute com Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
mvnw.cmd spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

## API

### Cadastrar dados sensíveis

```http
POST /dados-sensivel
Content-Type: application/json
```

### Consultar dados por ID

```http
GET /dados-sensivel/{id}
```

### Atualizar dados sensíveis

```http
PUT /dados-sensivel/{id}
Content-Type: application/json
```

### Excluir dados sensíveis

```http
DELETE /dados-sensivel/{id}
```

Os detalhes dos contratos de entrada e saída podem ser consultados na documentação OpenAPI da aplicação.

## Swagger / OpenAPI

Com a aplicação em execução:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação também pode ser consultada através da integração do Swagger com o Actuator.

## H2 Console

O console do H2 está disponível em:

```text
http://localhost:8080/h2-console
```

## Actuator

Endpoint de saúde:

```text
http://localhost:8080/actuator/health
```

Endpoint de métricas:

```text
http://localhost:8080/actuator/metrics
```

## Docker

Para gerar o artefato da aplicação:

```bash
mvn clean package
```

Para gerar a imagem Docker:

```bash
docker build -t criptografia .
```

Para executar o container:

```bash
docker run -p 8080:8080 criptografia
```

## Testes

Execute os testes com:

```bash
mvn test
```

## Status

Projeto de estudo desenvolvido para praticar desenvolvimento de APIs REST com Spring Boot, persistência com JPA/H2, tratamento de dados sensíveis e integração com recursos de monitoramento da aplicação.
