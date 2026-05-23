# ParkSys

Sistema de gerenciamento de estacionamento desenvolvido com Java 21 e Spring Boot 3.

O sistema controla entrada e saida de veiculos, calcula cobrancas automaticamente com base no tipo de veiculo e gerencia vagas.

## Tecnologias

- Java 21
- Spring Boot 3.5
- Spring Data JPA / Hibernate
- Spring Security
- PostgreSQL 16
- Flyway (migrations)
- Lombok
- Swagger / OpenAPI
- Docker Compose

## Como rodar

Requisitos: Docker e Java 21 instalados.

```bash
# 1. Subir o banco de dados
docker-compose up -d

# 2. Rodar o projeto
./mvnw spring-boot:run
```

A API fica disponivel em `http://localhost:8080`

Documentacao Swagger em `http://localhost:8080/swagger-ui.html`

## Endpoints

### Autenticacao

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| POST | `/api/v1/auth/registrar` | Criar conta |
| POST | `/api/v1/auth/login` | Fazer login |

### Veiculos

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| POST | `/api/v1/veiculos` | Cadastrar veiculo |
| GET | `/api/v1/veiculos` | Listar todos |
| DELETE | `/api/v1/veiculos/{id}` | Remover veiculo |

### Vagas

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| GET | `/api/v1/vagas` | Listar todas as vagas |
| GET | `/api/v1/vagas/livres` | Listar vagas disponiveis |

### Movimentacoes

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| POST | `/api/v1/movimentacoes/entrada` | Registrar entrada |
| PUT | `/api/v1/movimentacoes/{id}/saida` | Registrar saida |
| GET | `/api/v1/movimentacoes/ativas` | Veiculos estacionados agora |
| GET | `/api/v1/movimentacoes` | Historico completo |

## Estrutura do projeto

```
src/main/java/com/parksys/
    config/          SecurityConfig
    controller/      AuthController, VeiculoController, VagaController, MovimentacaoController
    dto/             VeiculoRequest, VeiculoResponse, EntradaRequest, SaidaResponse, LoginRequest, LoginResponse
    exception/       BusinessException, GlobalExceptionHandler
    model/           Veiculo (abstrato), Carro, Moto, Caminhonete, Vaga, Movimentacao, Usuario
    repository/      VeiculoRepository, VagaRepository, MovimentacaoRepository, UsuarioRepository
    service/         VeiculoService, VagaService, MovimentacaoService, CobrancaService, AuthService
```

## Regras de negocio

- Placa duplicada nao pode ser cadastrada
- Veiculo que ja esta estacionado nao pode entrar de novo
- Vaga ocupada nao pode ser usada
- Saida de movimentacao ja finalizada nao e permitida

## Cobranca

| Tipo | Multiplicador | Exemplo (90 min) |
|------|--------------|-------------------|
| Carro | 1.0x | R$ 8,00 |
| Moto | 0.5x | R$ 4,00 |
| Caminhonete | 1.5x | R$ 12,00 |

Primeira hora: R$ 5,00. Hora adicional: R$ 3,00 (arredondado pra cima).

## Banco de dados

4 tabelas: `veiculos`, `vagas`, `movimentacoes`, `usuarios`

Migrations gerenciadas pelo Flyway em `src/main/resources/db/migration/`.

## OOP

- **Heranca**: Veiculo (abstrato) -> Carro, Moto, Caminhonete
- **Polimorfismo**: cada tipo implementa `calcularValor()` com multiplicador diferente
- **Encapsulamento**: atributos private com getters/setters via Lombok
