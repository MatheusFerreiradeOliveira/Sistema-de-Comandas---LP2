package com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.PedidoDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.CompraService;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.PedidoService;

@RestController
@RequestMapping("/compras")
public class CompraResource {
	@Autowired
	public CompraService compraService;
	
	@Autowired
	public PedidoService servicePedido;
	
	@GetMapping
	public List<CompraDTO> findAll(){
		return compraService.findAll();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraDTO> findById(@PathVariable String id){
		CompraDTO obj = compraService.findById(id);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/pedidos")
	public List<Pedido> findPedidosById(@PathVariable String id){
		return compraService.findPedidoById(id);
	} 
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CompraDTO compraDTO) {
		Compra obj = compraService.insert(compraDTO);
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	//@PutMapping("/{id}/pedidos")
	@PostMapping("/{id}/pedidos")
	public ResponseEntity<Void> insertPedido(@PathVariable String id, @RequestBody List<PedidoDTO> list) {
		Compra compra = compraService.insertNewPedido(id, list);
		
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(compra.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
		/*
		return ResponseEntity.noContent().build();
		*/
	}
	/*
	@PutMapping("/{id}")
	public ResponseEntity<Compra> update(@PathVariable String id, 
											@RequestBody Compra p){
		p.setId(id);
		p=serviceCompra.update(p);
		return ResponseEntity.noContent().build();
	}
	
	/*
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		serviceCompra.deleteById(id);
		return ResponseEntity.noContent().build();
	}
*/
}
