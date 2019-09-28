package com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraEncerrarDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.NewCompraInDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.PedidoDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources.util.URL;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.CompraService;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.PedidoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/compras")
public class CompraResource {
	@Autowired
	public CompraService compraService;
	
	@Autowired
	public PedidoService servicePedido;
	
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Compras Listadas com Sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ApiOperation(value = "Lista todas Compras", notes = "Traz uma lista paginada de todas as compras", response = Compra.class, responseContainer = "List")
	@GetMapping(produces="application/json")
	public Page<Compra> findAll(){
		return compraService.findAll();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Compra> findById(@PathVariable String id){
		Compra obj = compraService.findById(id);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/pedidos")
	public Page<Pedido> findPedidosById(@PathVariable String id){
		return compraService.findPedidoByCompra(id);
	} 
	/*
	@GetMapping("/mesasearch")
	public ResponseEntity<List<Compra>> searchByMesa(@RequestParam(value="mesa", defaultValue="") String text){
		text= URL.decodeParam(text);
		List<Compra> obj=compraService.searchByMesa(text);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	*/
	
	@GetMapping("/search")
	public ResponseEntity<Page<Compra>> searchCompraByMesa(
			@RequestParam(value="mesa", required = false, defaultValue="") String text,
			@RequestParam(value="minDate", required= false, defaultValue="") String minDate,
			@RequestParam(value="maxDate", required = false, defaultValue="") String maxDate,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size  ){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		Page<Compra> obj = compraService.searchCompraByMesa(text, min, max,size,page);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody NewCompraInDTO compra) {
		Compra obj = compraService.insert(compra);
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	

	@PatchMapping("/{id}/pedidos")
	public ResponseEntity<Void> insertPedido(@PathVariable String id, @RequestBody List<PedidoDTO> list) {
		compraService.insertNewPedido(id, list);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/{id}/encerrar")
	public ResponseEntity<CompraDTO> encerrarConta(@PathVariable String id) {
		CompraDTO obj =compraService.encerrarCompra(id);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	@PatchMapping("/{id}/encerrar")
	public ResponseEntity<Void> update(@PathVariable String id, 
										@RequestBody CompraEncerrarDTO compra){
		compra.setId(id);
		Compra obj = new Compra(compra);
		System.out.println(obj);
		obj = compraService.update(obj);
		return ResponseEntity.noContent().build();
	}
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateCompra(@PathVariable String id, 
										@RequestBody Compra compra){
		compra.setId(id);
		compra = compraService.update(compra);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		compraService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
