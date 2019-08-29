package com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	/*@DeleteMapping("/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String id){
		service.deleteById(id);
	}*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
