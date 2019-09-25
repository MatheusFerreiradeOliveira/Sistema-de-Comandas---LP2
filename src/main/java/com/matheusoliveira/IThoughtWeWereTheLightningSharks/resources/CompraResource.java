package com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

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

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.PedidoDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.resources.util.URL;
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
	/*
	@GetMapping("/mesasearch")
	public ResponseEntity<List<Compra>> searchByMesa(@RequestParam(value="mesa", defaultValue="") String text){
		text= URL.decodeParam(text);
		List<Compra> obj=compraService.searchByMesa(text);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	*/
	
	@GetMapping("/searchPage")
	public ResponseEntity<Page<Compra>> searchCompraByMesa(
			@RequestParam(value="mesa", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate,
			/*Pageable pageable*/
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
                    
            ){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		Page<Compra> obj = compraService.searchCompraByMesa(text, min, max,size,page);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<CompraDTO>> searchCompraByMesa2(
			@RequestParam(value="mesa", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate,
			/*Pageable pageable*/
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
                    
            ){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<CompraDTO> obj = compraService.searchCompraByMesa2(text, min, max,size,page);
		return (obj!=null) ? ResponseEntity.ok(obj) : 
			ResponseEntity.notFound().build();
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
	
	
	@PutMapping("/{id}/pedidos")
	//@PostMapping("/{id}/pedidos")
	public ResponseEntity<Void> insertPedido(@PathVariable String id, @RequestBody List<PedidoDTO> list) {
		Compra compra = compraService.insertNewPedido(id, list);
		//System.out.println(compra.toString());
		/*
		URI uri=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(compra.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
		*/
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, 
										@RequestBody Compra compra){
		compra.setId(id);
		compra=compraService.update(compra);
		//System.out.println(compra.toString());
		return ResponseEntity.noContent().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		compraService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
