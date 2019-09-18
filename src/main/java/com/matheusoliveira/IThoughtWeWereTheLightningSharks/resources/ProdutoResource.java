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

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	public ProdutoService service;
	
	@GetMapping
	public List<Produto> findAll(){
		return service.findAll();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable String id){
		Produto obj=service.findById(id);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable String id,@RequestBody Produto p){
		p.setId(id);
		p=service.update(p);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Produto p) {
		Produto obj = service.insert(p);
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
