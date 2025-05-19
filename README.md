# Sistema de Consulta de Créditos

## Visão Geral

Este projeto implementa uma **API RESTful** em Spring Boot para consulta de créditos constituídos e um **front-end** em Angular para consumo e exibição dos dados.  
A solução está toda containerizada com Docker, incluindo banco de dados PostgreSQL e broker Kafka.

---

## Como Executar com Docker

Esta seção descreve como subir toda a aplicação (banco, mensageria, API e front-end) usando Docker e Docker Compose.

### Pré-requisitos

- Docker (v20+)
- Docker Compose (v1.27+)

### Estrutura de Containers

O `docker-compose.yml` orquestra os seguintes serviços:

| Serviço   | Imagem / Build               | Porta Local        | Descrição                                |
|-----------|------------------------------|--------------------|------------------------------------------|
| **db**    | `postgres:15`                | `5432:5432`        | Banco PostgreSQL                         |
| **zookeeper** | `confluentinc/cp-zookeeper:7.4.4` | `22181:2181` | Coordenação para Kafka                  |
| **kafka** | `confluentinc/cp-kafka:7.4.4`| `29092:29092`      | Broker Kafka                             |
| **api**   | build `creditos-api/Dockerfile` | `8080:8080`    | Spring Boot REST API                     |
| **frontend** | build `creditos-frontend/Dockerfile` | `4200:80` | Angular + Nginx servindo a SPA           |

### Passos para subir os containers

1. **No diretório raiz do repositório**, execute:
   ```bash
   docker-compose up --build -d
---

## Funcionalidades

- **Consulta por NFS-e**  
  `GET /api/creditos/{numeroNfse}`  
  Retorna lista de créditos associados a uma NFS-e.

- **Consulta por número de crédito**  
  `GET /api/creditos/credito/{numeroCredito}`  
  Retorna os detalhes de um crédito específico.

- **Publicação de eventos**  
  Cada consulta publica uma mensagem em Kafka para auditoria.

---

## Tecnologias

- **Back-end**  
  Java 17 • Spring Boot • Spring Data JPA • Flyway • Kafka (Spring Kafka) • JUnit + Mockito

- **Front-end**  
  Angular 9 • TypeScript • Reactive Forms • SCSS • HTTPClient • OnPush change detection

- **Infraestrutura**  
  PostgreSQL • Zookeeper • Kafka • Docker • Docker Compose

---

## Estrutura do Repositório
.
├── creditos-api/
│ ├── src/
│ ├── build.gradle
│ └── Dockerfile
├── creditos-frontend/
│ ├── src/
│ ├── package.json
│ └── Dockerfile
└── docker-compose.yml


- **creditos-api/**: código da API Spring Boot  
- **creditos-frontend/**: aplicação Angular  
- **docker-compose.yml**: orquestração de containers

---

## Front-end

Todo o front-end foi implementado em **Angular 9**, com arquitetura modular e componentes reutilizáveis.

### Componentes principais

- **SearchFormComponent**  
  Formulário reativo com validação (apenas números e hífens). Emite evento `(search)` com `{ filtro, isNfse }`.

- **CreditTableComponent**  
  Recebe `@Input() data: CreditDto[]` e renderiza a tabela com cabeçalho colorido, linhas alternadas e cantos arredondados.

- **CreditSearchComponent**  
  Container que coordena o fluxo: recebe o evento do formulário, chama o `CreditService`, mantém `loading`, `error` e passa `resultados` para a tabela.




