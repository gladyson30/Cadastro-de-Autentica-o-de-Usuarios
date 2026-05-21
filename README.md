# 👤 Usuários API

API RESTful de cadastro e gerenciamento de usuários com autenticação via Spring Security, OAuth2 e login social com Google.

---

## 🚀 Tecnologias

| Tecnologia | Versão | Descrição |
|---|---|---|
| ☕ Java | 25 | Linguagem principal |
| 🍃 Spring Boot | 4.0.4 | Framework base da aplicação |
| 🔒 Spring Security | — | Autenticação e autorização |
| 🔑 OAuth2 Authorization Server | — | Servidor de autorização com JWT |
| 🌐 OAuth2 Client | — | Login social com Google |
| 🐘 PostgreSQL | — | Banco de dados relacional |
| 🗄️ Spring Data JPA | — | Persistência e acesso a dados |
| ⚡ Lombok | — | Redução de boilerplate |

---

## 📋 Funcionalidades

- ✅ Cadastro de usuários com senha criptografada (BCrypt)
- ✅ Login com autenticação customizada (email + senha)
- ✅ Login social com Google (OAuth2)
- ✅ Autorização por roles (`USER`, `GOOGLE`, `ADMIN`)
- ✅ CRUD completo de usuários
- ✅ Cadastro de clientes OAuth2 com Authorization Server
- ✅ Suporte a fluxos `authorization_code` e `client_credentials`
- ✅ Tokens JWT auto-contidos (SELF_CONTAINED)

---

## 🔐 Autenticação

A API utiliza dois mecanismos de autenticação:

- **Form Login / HTTP Basic** — para usuários cadastrados localmente
- **OAuth2 Login (Google)** — login social, criando o usuário automaticamente na primeira vez

---

## 📦 Endpoints

### Usuários — `/usuarios`

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| `POST` | `/usuarios` | Cadastrar novo usuário | Pública |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID | Autenticado |
| `GET` | `/usuarios/listar` | Listar todos os usuários | `ROLE_ADMIN` |
| `PUT` | `/usuarios/{id}` | Atualizar usuário | Autenticado |
| `DELETE` | `/usuarios/{id}` | Deletar usuário | Autenticado |

### Clientes OAuth2 — `/clientes`

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| `POST` | `/clientes` | Cadastrar novo cliente OAuth2 | Pública |

---

## 📂 Estrutura do Projeto

```
src/main/java/com/example/usuariosApi/
├── Usuario/
│   ├── controller/         # Controllers REST
│   ├── Service/            # Regras de negócio
│   ├── Repository/         # Acesso ao banco
│   ├── Entity/             # Entidades JPA
│   ├── Dtos/               # DTOs de entrada e saída
│   ├── Execoes/            # Exceções customizadas
│   ├── security/           # Autenticação customizada
│   ├── configuraçoes/      # Configuração do Spring Security
│   └── Cliente/            # Módulo de clientes OAuth2
│       ├── Controler/
│       ├── service/
│       ├── repository/
│       ├── entity/
│       ├── execoes/
│       ├── securite/
│       └── Configuration/  # Authorization Server
```

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java 25+
- Maven
- PostgreSQL

### Configuração do banco

Crie um banco de dados PostgreSQL chamado `usuarios`:

```sql
CREATE DATABASE usuarios;
```

### Configuração do `application.yaml`

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/usuarios
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
```

### Rodando

```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8081`

---

## 📝 Exemplo de requisição

### Cadastrar usuário

```http
POST /usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "roles": ["USER"]
}
```

### Cadastrar cliente OAuth2

```http
POST /clientes
Content-Type: application/json

{
  "clienteid": "meu-client",
  "clienteSecret": "secret123",
  "redirectURI": "http://localhost:8080/callback",
  "scope": "openid"
}
```

---

## 👨‍💻 Autor

Feito com ☕ e Java.
