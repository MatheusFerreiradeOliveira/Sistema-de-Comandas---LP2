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

#### Usuários

| Method | Route | Header | Params |
| --- | --- | --- | --- | 
| GET | `http://127.0.0.1:8082/usuarios` | Content-Type : application/json | none |

| Return |
| --- | 
```js
{
    "content": [
        {
            "id": "5d8df1b7dad0190768dd6cb7",
            "nome": "Joaquin",
            "permissoes": [
                {
                    "id": "5d8df1b7dad0190768dd6cb3",
                    "descricao": "Adicionar mesas"
                },
                {
                    "id": "5d8df1b7dad0190768dd6cb4",
                    "descricao": "Inserir produto"
                },
                {
                    "id": "5d8df1b7dad0190768dd6cb5",
                    "descricao": "Realizar pedido"
                },
                {
                    "id": "5d8df1b7dad0190768dd6cb6",
                    "descricao": "Listar compras"
                }
            ]
        },
        {
            "id": "5d8df1b7dad0190768dd6cb8",
            "nome": "Garçoneide",
            "permissoes": [
                {
                    "id": "5d8df1b7dad0190768dd6cb5",
                    "descricao": "Realizar pedido"
                }
            ]
        }
    ],
    "last": true,
    "totalPages": 1,
    "totalElements": 10,
    "size": 10,
    "number": 0,
    "sort": null,
    "first": true,
    "numberOfElements": 2
}
```



#### Compras
  
| Método | Rota | Retorno |
| --- | --- | --- |
| GET | `http://127.0.0.1:8082/compras` | Retorna todas as compras |
| GET | `http://127.0.0.1:8082/compras/{ID}` | Retorna a compra com id passado no parâmetro |
| GET | `http://127.0.0.1:8082/compras/{ID}/pedidos` | Retorna os pedidos da compra com id passado no parâmetro |
| GET | `http://127.0.0.1:8082/compras/search` | Retorna a lista de compras da mesa passada no filtro |
| POST  | `http://127.0.0.1:8082/compras`  | Adiciona uma nova compra |
| POST  | `http://127.0.0.1:8082/compras/{ID}/pedidos`  | Adiciona um novo pedido na compra com id passado no parâmetro |
| PATH  | `http://127.0.0.1:8082/compras/{ID}`  | Altera os atributos da compras com id passado no parâmetro  |
| DELETE  | `http://127.0.0.1:8082/compras/{ID}`  | Deleta a compra com id passado no parâmetro |

> Obs.: quando inserir uma nova compra, deve-se deixar os camposde encerramento, cartao, dinheiro vazios, 
pois serao atualizadas quando ele realizar  GET: `http://127.0.0.1:8082/compras/{ID}/encerrar`


- Exemplo de Body para cadastrar nova compra em JSON

```
{
    "abertura": "2019-12-11T12:12:12",
    "mesa": "C",
    "pedidos": [
        {
            "qtdItens": 1,
            "peso": 1.0,
            "hora": "2019-12-11T12:12:12",
            "obs": "ao ponto",
            "produto":{ "id":"5d8d0289dad01905f8756dbd"}
            
        }
    ]
}

```

#### Pedidos
    
| Método | Rota | Retorno |
| --- | --- | --- |
| GET | `http://127.0.0.1:8082/pedidos` | Retorna todos os pedidos |
| GET | `http://127.0.0.1:8082/pedidos/{ID}` | Retorna o pedido com id passado no parâmetro |
| GET | `http://127.0.0.1:8082/pedidos/obssearch` | Retorna a lista de pedidos com a observação passada no filtro |
| PATH  | `http://127.0.0.1:8082/pedidos/{ID}`  | Altera os atributos do pedido com id passado no parâmetro |
| DELETE  | `http://127.0.0.1:8082/pedidos/{ID}`  | Deleta o pedido com id passado no parâmetro |

#### Produtos
  
| Método | Rota | Retorno |
| --- | --- | --- |
| GET | `http://127.0.0.1:8082/produtos` | Retorna todos os produtos |
| GET | `http://127.0.0.1:8082/produtos/{ID}` | Retorna o produto com id passado no parâmetro |
| POST  | `http://127.0.0.1:8082/produtos`  | Adiciona um novo produto |
| PATH  | `http://127.0.0.1:8082/produtos/{ID}`  | Altera os atributos do produto com id passado no parâmetro |
| DELETE  | `http://127.0.0.1:8082/produtos/{ID}`  | Deleta o produto com id passado no parâmetro |
  
- Exemplo de JSON

```
{
	"nome": "Batata frita",
	"valor": 5.0,
 	"peso": false
}
```

#### Usuários
  
| Método | Rota | Retorno |
| --- | --- | --- |
| GET | `http://127.0.0.1:8082/usuarios` | Retorna todos os usuarios |
| GET | `http://127.0.0.1:8082/usuarios/{ID}` | Retorna o usuario com id passado no parâmetro |
| POST  | `http://127.0.0.1:8082/usuario`  | Adiciona um novo usuario |
| PATH  | `http://127.0.0.1:8082/usuario/{ID}`  | Altera os atributos do usuario com id passado no parâmetro |
| DELETE  | `http://127.0.0.1:8082/usuario/{ID}`  | Deleta o usuario com id passado no parâmetro |
  
- Exemplo de JSON

```
{
	"nome": "Micaias",
	"permissoes": [
	]
}
```
