# Resumo

Implementar uma API RESTful.

Deverá ser realizado um crud parcial para o seguinte contexto.

Operações do CRUD a ser implementadas:

* Criar
* Ler

Eu como usuário gostaria de cadastrar `projetos` e cada projeto pode ter N `funcionários` e cada `funcionário` pode ter
N `projetos`.

Eu como usuário gostaria de listar projetos com seus respectivos funcionários.

**Atributos obrigátorios para as entidades:**

* Projeto:  (nome, data_criacao)
* Funcionario: (nome, cpf, email, salario)

# Instruções

- Usar java 11+
- Spring Boot ou Quarkus
- Usar Banco de dados relacional (não usar banco em memória)
- Documentar execução da aplicação
- Seguir o padrão RESTful
- Seguir Normalização do Banco de Dados
- Realizar testes unitários/integrados
- Enviar o código para o github

---

# Documentação do Projeto

## Entidades

### Funcionario

A classe Funcionario representa um funcionário que vai ser associado aos projetos no sistema de gestão de projetos.
A classe inclui os seguintes atributos:

| Atributo     | Tipo          | Descrição                                   | Restrições                                                                 |
|--------------|---------------|---------------------------------------------|----------------------------------------------------------------------------|
| **id**       | UUID          | Identificador único do funcionário          | Gerado automaticamente                                                     |
| **nome**     | String        | Nome completo do funcionário                | Não pode ser nulo                                                          |
| **cpf**      | String        | CPF do funcionário                          | Não pode ser nulo. Segue o padrão "000.000.000-00", símbolos são opcionais |
| **email**    | String        | Endereço de email do funcionário            | Precisa ser um email válido                                                |
| **salario**  | BigDecimal    | Valor de salário do funcionário             |                                                                            |
| **projetos** | List<Projeto> | Lista de projetos associados ao funcionário | Relacionamento many-to-many                                                |

### Projeto

A classe Projeto representa um projeto no sistema de gerenciamento de projetos. Um projeto pode ter vários funcionários
associados, e um funcionário pode estar em vários projetos. A classe inclui os seguintes atributos:

| Atributo         | Tipo              | Descrição                                   | Restrições                                       |
|------------------|-------------------|---------------------------------------------|--------------------------------------------------|
| **id**           | UUID              | Identificador único do projeto.             | Gerado automaticamente                           |
| **nome**         | String            | Nome do projeto                             | Não pode ser nulo                                |
| **data_criacao** | LocalDateTime     | Data de criação do projeto                  | Gerada automaticamente e não pode ser atualizada |
| **funcionarios** | List<Funcionario> | Lista de funcionários associados ao projeto | Relacionamento Many-to-Many                      |

---

## Descrição dos Endpoints

### POST /funcionarios

Cria um novo funcionário dentro da tabela.

**Requisição**:

```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@example.com",
  "salario": 5000.00
}
```

**Resposta**:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@example.com",
  "salario": 5000.00
}
```

### GET /funcionarios

Para listar todos os funcionários com seus projetos associados.

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "nome": "João Silva",
    "cpf": "12345678900",
    "email": "joao.silva@example.com",
    "salario": 5000.00
  }
]
```

### ### GET /funcionarios/{id}

Para buscar um funcionário específico, baseado no seu id.

**Request**: ```/funcionarios/550e8400-e29b-41d4-a716-446655440000```

**Resposta**:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@example.com",
  "salario": 5000.00
}
```

---

### POST /projetos

Cria projetos dentro da tabela, pode ser inserido com id do funcionário, caso já existente, ou com os dados para
inserção do funcionário durante o cadastro de projeto. É possível incluir nos dois métodos ao mesmo tempo.

**Request**:

```json
{
  "nome": "Projeto Alpha",
  "funcionarios": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440001"
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "nome": "João Silva",
      "cpf": "12345678900",
      "email": "joao.silva@example.com",
      "salario": 5000.00
    }
  ]
}
```

**Resposta**:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Projeto Alpha",
  "data_criacao": "2023-10-01T10:00:00",
  "funcionarios": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "nome": "Maria Souza",
      "cpf": "98765432100",
      "email": "maria.souza@example.com",
      "salario": 6000.00
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "nome": "João Silva",
      "cpf": "12345678900",
      "email": "joao.silva@example.com",
      "salario": 5000.00
    }
  ]
}
```

### GET /projetos

Para listar todos os projetos com seus funcionários associados.

**Resposta**:

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "nome": "Projeto Alpha",
    "data_criacao": "2023-10-01T10:00:00",
    "funcionarios": [
      {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "nome": "João Silva",
        "cpf": "12345678900",
        "email": "joao.silva@example.com",
        "salario": 5000.00
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nome": "Maria Souza",
        "cpf": "98765432100",
        "email": "maria.souza@example.com",
        "salario": 6000.00
      }
    ]
  }
]
```

### GET /projetos/{id}

Para buscar um projeto específico, baseado no seu id.

**Request**: ```/funcionarios/550e8400-e29b-41d4-a716-446655440000```

**Resposta**:

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Projeto Alpha",
  "data_criacao": "2023-10-01T10:00:00",
  "funcionarios": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "nome": "João Silva",
      "cpf": "12345678900",
      "email": "joao.silva@example.com",
      "salario": 5000.00
    }
  ]
}
```