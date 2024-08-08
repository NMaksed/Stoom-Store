# Stoom Store

Stoom Store é uma aplicação de backend para gerenciar uma loja online, permitindo operações de CRUD (Create, Read, Update, Delete) em produtos, marcas e categorias.

## Tecnologias Utilizadas

- **Java**: Linguagem principal utilizada para o desenvolvimento da aplicação.
- **Spring Boot**: Framework utilizado para simplificar a configuração e o desenvolvimento do aplicativo.
- **Spring Data JPA**: Para interações de persistência com o banco de dados.
- **Hibernate**: Implementação do JPA para ORM.
- **MySQL**: Banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Lombok**: Biblioteca para reduzir o boilerplate de código Java.
- **Jackson**: Biblioteca para serialização e desserialização de objetos Java para JSON.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.

## Funcionalidades

- **Gerenciamento de Produtos**: Cadastro, leitura, atualização e desativação de produtos.
- **Gerenciamento de Marcas**: Cadastro, leitura, atualização e desativação de marcas.
- **Gerenciamento de Categorias**: Cadastro, leitura, atualização e desativação de categorias.

## Endpoints

### Produtos

- `GET /products`: Lista todos os produtos.
- `GET /products/{id}`: Retorna um produto específico pelo ID.
- `POST /products`: Adiciona um novo produto.
- `PATCH /products/{id}`: Atualiza parcialmente um produto.
- `PATCH /products/{id}/deactivate`: Desativa um produto.

### Marcas

- `GET /brands`: Lista todas as marcas.
- `GET /brands/{id}`: Retorna uma marca específica pelo ID.
- `POST /brands`: Adiciona uma nova marca.
- `PATCH /brands/{id}`: Atualiza parcialmente uma marca.
- `PATCH /brands/{id}/deactivate`: Desativa uma marca.

### Categorias

- `GET /categories`: Lista todas as categorias.
- `GET /categories/{id}`: Retorna uma categoria específica pelo ID.
- `POST /categories`: Adiciona uma nova categoria.
- `PATCH /categories/{id}`: Atualiza parcialmente uma categoria.
- `PATCH /categories/{id}/deactivate`: Desativa uma categoria.

## Exemplo de Uso

### Criar uma Nova Marca

**Requisição:**
```json
POST /brands
{
    "name": "Nova Marca",
    "active": true
}
