package com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.PedidoDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources.util.URL;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	public PedidoService service;
	
	/** alterar pra pegar todos pedidos em um periodo*/
	@GetMapping
	public Page<Pedido> findAll(){
		return service.findAll();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable String id){
		Pedido obj=service.findById(id);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	} 
	
	@GetMapping("/obssearch")
	public ResponseEntity<Page<Pedido>> findByTitle(@RequestParam(value="obs", defaultValue="") String text){
		text= URL.decodeParam(text);
		Page<Pedido> obj=service.findByObs(text);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	} 
	
	@GetMapping("/search")
	public ResponseEntity<Page<Pedido>> searchCompraByMesa(
			@RequestParam(value="produto", defaultValue="") String text,
			@RequestParam(value="minate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate,
			@RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
                    
            ){
		text= URL.decodeParam(text);
		Date min =URL.convertDate(minDate, new Date(0L));
		Date max =URL.convertDate(maxDate, new Date());
		Page<Pedido> obj=service.searchPedido(text, min, max, size, page);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable String id, @RequestBody PedidoDTO p){
		p.setId(id);
		//Pedido pedido=service.update(p);
		service.update(p);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PedidoDTO p) {
		Pedido obj = service.insert(p);
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
