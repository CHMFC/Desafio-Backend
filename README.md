# Desafio Comics API

## Descrição

O projeto **Desafio Comics API** é uma API RESTful desenvolvida para gerenciar quadrinhos e cupons de desconto em quadrinhos com diferentes níveis de raridade. A API permite criar quadinhos, atualiza-los, lista-los e gerar novos cupons e recuperar cupons com base na raridade do quadrinho.

## Tecnologias Utilizadas

- Java 17
- Swagger
- Spring Boot
- Spring Data JPA
- Jakarta Persistence (JPA)
- Lombok
- MySQL

## Estrutura do Projeto

### Entidades

- **Cupom**: Representa um cupom de desconto no sistema. Cada cupom possui um código único, uma raridade associada, um valor de desconto e uma data de validade.
- **Quadrinho**: Representa o quadrinho com informações como nome, autor, raridade...

### Repositório

- **CupomRepository**: Interface que estende `JpaRepository`.

### Serviços

- **CupomService**: Serviço responsável pela lógica de negócios relacionada aos cupons. Contém métodos para gerar um novo cupom e recuperar cupons com base na raridade.
- **QuadrinhoService**: Classe de serviço responsável pela lógica de negócios relativa aos quadrinhos. Inclui métodos como adicionar, atualizar, remover e buscar quadrinhos.

### Controladores

- **CupomController**: Controlador REST que fornece endpoints para gerar novos cupons e recuperar cupons por raridade.
**QuadrinhoController**: Controlador REST que fornece endpoints para criar, atualizar, remover e buscar quadrinhos. Inclui também funcionalidades para lidar com cupons associados aos quadrinhos.
  
## Endpoints da API

### Quadrinho Controller

#### Criar um Novo Quadrinho

- **URL**: `/api/v1/quadrinhos/cadastrar`
- **Método HTTP**: `POST`
- **Descrição**: Cria um novo quadrinho no sistema.
- **Parâmetros**:
  - Request Body: Objeto `Quadrinho` contendo as informações do quadrinho a ser criado.
- **Resposta**:
  - `201 Created`: Retorna o quadrinho recém-criado.

#### Atualizar Quadrinho Existente

- **URL**: `/api/v1/quadrinhos/atualizar/{id}`
- **Método HTTP**: `PUT`
- **Descrição**: Atualiza as informações de um quadrinho existente.
- **Parâmetros**:
  - `id` (Path Variable): O identificador único do quadrinho a ser atualizado.
  - Request Body: Objeto `Quadrinho` contendo os dados atualizados.
- **Resposta**:
  - `200 OK`: Retorna o quadrinho atualizado.

#### Remover Quadrinho

- **URL**: `/api/v1/quadrinhos/deletar/{id}`
- **Método HTTP**: `DELETE`
- **Descrição**: Remove um quadrinho do sistema.
- **Parâmetros**:
  - `id` (Path Variable): O identificador único do quadrinho a ser removido.
- **Resposta**:
  - `204 No Content`: Indica que o quadrinho foi removido com sucesso.

#### Buscar Quadrinho por ID

- **URL**: `/api/v1/quadrinhos/buscar/id/{id}`
- **Método HTTP**: `GET`
- **Descrição**: Recupera um quadrinho pelo seu identificador único.
- **Parâmetros**:
  - `id` (Path Variable): O identificador único do quadrinho.
- **Resposta**:
  - `200 OK`: Retorna o quadrinho com o ID correspondente.

#### Buscar Todos os Quadrinhos

- **URL**: `/api/v1/quadrinhos/buscar/todos`
- **Método HTTP**: `GET`
- **Descrição**: Recupera uma lista de todos os quadrinhos cadastrados no sistema.
- **Resposta**:
  - `200 OK`: Retorna uma lista de quadrinhos.

#### Buscar Quadrinhos por Raridade

- **URL**: `/api/v1/quadrinhos/buscar/raridade/{raridade}`
- **Método HTTP**: `GET`
- **Descrição**: Recupera uma lista de quadrinhos com base na raridade.
- **Parâmetros**:
  - `raridade` (Path Variable): A raridade dos quadrinhos.
- **Resposta**:
  - `200 OK`: Retorna uma lista de quadrinhos que correspondem à raridade fornecida.

### Cupom Controller

#### Gerar um Novo Cupom

- **URL**: `/api/v1/cupons/gerar/{raridade}`
- **Método HTTP**: `POST`
- **Descrição**: Gera um novo cupom com base na raridade do quadrinho.
- **Parâmetros**:
  - `raridade` (Path Variable): A raridade do quadrinho. Pode ser `raro` ou qualquer outro valor definido no enum `Quadrinho.Raridade`.
- **Resposta**:
  - `200 OK`: Retorna o cupom recém-criado.

#### Recuperar Cupons por Raridade

- **URL**: `/api/v1/cupons/raridade/{raridade}`
- **Método HTTP**: `GET`
- **Descrição**: Recupera uma lista de cupons com base na raridade do quadrinho.
- **Parâmetros**:
  - `raridade` (Path Variable): A raridade do quadrinho.
- **Resposta**:
  - `200 OK`: Retorna uma lista de cupons que correspondem à raridade fornecida.

## Configuração do Ambiente de Desenvolvimento

### Pré-requisitos

- Java 17
- Maven
