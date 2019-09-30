# Sistema de Comandas

## Resumo

> Este é uma API REST simples, onde é implementado o back-end, com os objetivos de controle de estoque e auxilio no atendimento de uma barraca de praia ou restaurante.

## Ferramentas utiliadas

- Java
- Spring Boot
- Maven
- MongoDB
- Postman ou Insomnia

## Requisições  
  
> Obs.: O atributo id é obrigatório para atualizar qualquer entidade.

## Swagger

> Para obter mais detalhes sobre as requisisções utilizadas, use a API de documentação Swagger já configurada no aplicação. Para isso clone o projeto, execute como spring boot app, abra no navegador http://localhost:8082/swagger-ui.html e pronto! 

#### Compras
  
| Método | Rota | Retorno | Header | Params |
| --- | --- | --- | --- | --- | 
| GET | `http://127.0.0.1:8082/compras` | Retorna todas as compras | Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/compras/{ID}` | Retorna a compra com id passado no parâmetro | Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/compras/{ID}/pedidos` | Retorna os pedidos da compra com id passado no parâmetro | Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/compras/search` | Retorna a lista de compras da mesa passada no filtro | Content-Type : application/json | none |
| POST  | `http://127.0.0.1:8082/compras`  | Adiciona uma nova compra | Content-Type : application/json | none |
| POST  | `http://127.0.0.1:8082/compras/{ID}/pedidos`  | Adiciona um novo pedido na compra com id passado no parâmetro | Content-Type : application/json | none |
| PATH  | `http://127.0.0.1:8082/compras/{ID}`  | Altera os atributos da compras com id passado no parâmetro  | Content-Type : application/json | none |
| DELETE  | `http://127.0.0.1:8082/compras/{ID}`  | Deleta a compra com id passado no parâmetro | Content-Type : application/json | none |

> Obs.: quando inserir uma nova compra, deve-se deixar os camposde encerramento, cartao, dinheiro vazios, 
pois serao atualizadas quando ele realizar  GET: `http://127.0.0.1:8082/compras/{ID}/encerrar`



#### Pedidos
    
| Método | Rota | Retorno | Header | Params |
| --- | --- | --- | --- | --- | 
| GET | `http://127.0.0.1:8082/pedidos` | Retorna todos os pedidos |  Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/pedidos/{ID}` | Retorna o pedido com id passado no parâmetro | Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/pedidos/obssearch` | Retorna a lista de pedidos com a observação passada no filtro | Content-Type : application/json | none |
| PATH  | `http://127.0.0.1:8082/pedidos/{ID}`  | Altera os atributos do pedido com id passado no parâmetro | Content-Type : application/json | none |
| DELETE  | `http://127.0.0.1:8082/pedidos/{ID}`  | Deleta o pedido com id passado no parâmetro | Content-Type : application/json | none |

#### Produtos

| Método | Rota | Retorno | Header | Params |
| --- | --- | --- | --- | --- | 
| GET | `http://127.0.0.1:8082/produtos` | Retorna todos os produtos | Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/produtos/{ID}` | Retorna o produto com id passado no parâmetro | Content-Type : application/json | none |
| POST  | `http://127.0.0.1:8082/produtos`  | Adiciona um novo produto | Content-Type : application/json | none |
| PATH  | `http://127.0.0.1:8082/produtos/{ID}`  | Altera os atributos do produto com id passado no parâmetro | Content-Type : application/json | none |
| DELETE  | `http://127.0.0.1:8082/produtos/{ID}`  | Deleta o produto com id passado no parâmetro | Content-Type : application/json | none |

#### Usuários
  
| Método | Rota | Retorno | Header | Params |
| --- | --- | --- | --- | --- | 
| GET | `http://127.0.0.1:8082/usuarios` | Retorna todos os usuarios | Content-Type : application/json | none |
| GET | `http://127.0.0.1:8082/usuarios/{ID}` | Retorna o usuario com id passado no parâmetro | Content-Type : application/json | none |
| POST  | `http://127.0.0.1:8082/usuario`  | Adiciona um novo usuario | Content-Type : application/json | none |
| PATH  | `http://127.0.0.1:8082/usuario/{ID}`  | Altera os atributos do usuario com id passado no parâmetro | Content-Type : application/json | none |
| DELETE  | `http://127.0.0.1:8082/usuario/{ID}`  | Deleta o usuario com id passado no parâmetro | Content-Type : application/json | none |
  
