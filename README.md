# Sistema de Comandas

## Requisições  
  
  
> Obs.: O atributo id é obrigatório para atualizar qualquer entidade. No caso da entidade de produto, além do id, também é obrigatório conter o atributo available.
  
  
#### Compras
  
  
| Método | Rota | Retorno |
| --- | --- | --- |
| GET | `http://127.0.0.1:8082/compras` | Retorna todas as compras |
| GET | `http://127.0.0.1:8082/compras/{ID}` | Retorna a compra com id passado no parâmetro |
| GET | `http://127.0.0.1:8082/compras/{ID}/pedidos` | Retorna os compras da compra com id passado no parâmetro |
| GET | `http://127.0.0.1:8082/compras/search` | Retorna a lista de compras da mesa passada no filtro |
| POST  | `http://127.0.0.1:8082/compras`  | Adiciona uma nova compra |
| POST  | `http://127.0.0.1:8082/compras/{ID}/pedidos`  | Adiciona um novo pedido na compra com id passado no parâmetro |
| PATH  | `http://127.0.0.1:8082/compras/{ID}`  | Altera os atributos da compras com id passado no parâmetro  |
| DELETE  | `http://127.0.0.1:8082/compras/{ID}`  | Deleta a compra com id passado no parâmetro |
    
- Exemplo de JSON

```
{
	"abertura": "AAAA-MM-DD HH:MM:SS",
	"encerramento": "AAAA-MM-DD HH:MM:SS",
	"mesa":,"A",
	"cartao": 0.0,
	"dinheiro": 50.0,
	"compras": [
		{
	 		"qtdItens": 1,
	 		"total": 50.0,
	 		"peso": false,
	 		"hora": "AAAA-MM-DD HH:MM:SS",
			"obs": "Sem sal",
	 		"produto": "Peixe frito"
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
| POST  | `http://127.0.0.1:8082/pedidos`  | Adiciona um novo pedido |
| PATH  | `http://127.0.0.1:8082/pedidos/{ID}`  | Altera os atributos do pedido com id passado no parâmetro |
| DELETE  | `http://127.0.0.1:8082/pedidos/{ID}`  | Deleta o pedido com id passado no parâmetro |
  
- Exemplo de JSON

```
{
	"qtdItens": 1,
	"total": 50.0,
 	"peso": false,
	"hora": "AAAA-MM-DD HH:MM:SS",
	"obs": "Sem sal",
	"produto": "Peixe frito"
}
```
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