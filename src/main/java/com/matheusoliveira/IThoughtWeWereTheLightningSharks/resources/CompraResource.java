package com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.CompraService;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.PedidoService;

@RestController
@RequestMapping("/compras")
public class CompraResource {
	@Autowired
	public CompraService serviceCompra;
	
	@Autowired
	public PedidoService servicePedido;
	
	@GetMapping
	public List<Compra> findAll(){
		return serviceCompra.findAll();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Compra> findById(@PathVariable String id){
		Compra obj=serviceCompra.findById(id);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	} 
	
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<List<Pedido>> findPedidosById(@PathVariable String id){
		Compra obj=serviceCompra.findById(id);
		List<Pedido> lul = obj.getPedidos();
		return (obj!=null) ? ResponseEntity.ok(lul) : 
			ResponseEntity.notFound().build();
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Compra> findById(@PathVariable String id, 
											@RequestBody Compra p){
		p.setId(id);
		p=serviceCompra.update(p);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Compra p) {
		Compra obj = serviceCompra.insert(p);
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{id}/pedidos")
	public ResponseEntity<Void> insertPedido(@PathVariable String id,
												@RequestBody Pedido p) {
		Compra obj = serviceCompra.findById(id);
		obj.insertPedido(p);
		
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		serviceCompra.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
