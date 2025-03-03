# Fixly - Backend

Bem-vindo ao repositório do backend do Fixly, uma aplicação que conecta clientes a prestadores de serviços, facilitando a contratação de profissionais como eletricistas, encanadores, jardineiros, entre outros. Este documento fornece uma visão geral do projeto, instruções de configuração, tecnologias utilizadas e detalhes para contribuir com o desenvolvimento.

## Sumário

- [Descrição do Projeto](#descrição-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Execução da Aplicação](#execução-da-aplicação)

## Descrição do Projeto

O Fixly é uma plataforma que intermedia a comunicação entre clientes e prestadores de serviços. Baseado na localização do cliente, a aplicação exibe prestadores de serviço próximos, permitindo que o cliente solicite orçamentos e se comunique através de um chat em tempo real. O fluxo principal envolve cadastro/login de usuários, busca por prestadores, comunicação via chat, envio e aprovação de propostas, e avaliações pós-serviço.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Framework:** Spring Boot
    - Spring Security para autenticação e autorização
    - Spring Data JPA para interação com o banco de dados
    - Spring WebSocket para comunicação em tempo real
- **Banco de Dados:**
    - PostgreSQL para dados relacionais
- **Ferramentas de Build:** Maven ou Gradle
- **Containerização:** Docker para facilitar o deployment

## Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- Java Development Kit (JDK) 11 ou superior
- Maven ou Gradle
- Docker (opcional, para containerização)
- PostgreSQL

## Instalação e Configuração

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/fabiolmorais/fixly.git
   cd fixly
   ```

2. **Configure o banco de dados:**

    - Crie um banco de dados PostgreSQL:

      ```sql
      CREATE DATABASE fixly_db;
      ```

    - Atualize as credenciais no arquivo `application.properties` ou `application.yml`:

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/fixly_db
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha
      ```

3. **Instale as dependências:**

    - Se estiver usando Maven:

      ```bash
      mvn clean install
      ```

    - Se estiver usando Gradle:

      ```bash
      gradle build
      ```

## Estrutura do Projeto

A estrutura básica do projeto é a seguinte:

```
fixly/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── fixly/
│   │   │           ├── config/        # Classes de configurações
│   │   │           ├── controllers/   # Controladores REST
│   │   │           ├── dto/           # DTO's
│   │   │           ├── entities/      # Modelos de dados
│   │   │           ├── repositories/  # Repositórios JPA
│   │   │           ├── services/      # Serviços de negócio
│   │   │           └── FixlyApplication.java  # Classe principal
│   │   └── resources/
│   │       ├── application.properties # Configurações da aplicação
│   │       └── static/                # Arquivos estáticos (se aplicável)
│   └── test/
│       └── java/
│           └── com/
│               └── fixly/
│                   └── ...            # Classes de teste
├── Dockerfile                         # Dockerfile para containerização
├── pom.xml                            # Arquivo Maven de configuração
└── build.gradle                       # Arquivo Gradle de configuração
```

## Execução da Aplicação

1. **Executando localmente:**

    - Com Maven:

      ```bash
      mvn spring-boot:run
      ```

    - Com Gradle:

      ```bash
      gradle bootRun
      ```

   A aplicação estará disponível em `http://localhost:8080`.

2. **Executando com Docker:**

    - Construa a imagem Docker:

      ```bash
      docker build -t fixly-backend .
      ```

    - Execute o container:

      ```bash
      docker run -p 8080:8080 fixly-backend
      ```
