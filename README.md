# Aplicação de delivery para restaurantes

## Requisições  
  
  
> Obs.: O atributo id é obrigatório para atualizar qualquer entidade. No caso da entidade de produto, além do id, também é obrigatório conter o atributo available.
  
  
#### Administradores  
  
  
| Método | Rota | Retorno |
| --- | --- | --- |
| GET | `http://127.0.0.1:8080/admins` | Retorna todos os administradores |
| GET | `http://127.0.0.1:8080/admins/{ID}` | Retorna o administrador com id passado no parâmetro |
| POST  | `http://127.0.0.1:8080/admins`  | Retorna o administrador salvo com o ID  |
| PATH  | `http://127.0.0.1:8080/admins`  | Retorna o administrador atualizado  |
| DELETE  | `http://127.0.0.1:8080/admins/{ID}`  | Retorna uma string  |
  
  
- Exemplo de JSON

```
{
	"name": "Lucas",
	"email": "lucas@gmail.com",
	"senha": "12345"
}
```