package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.CompraRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PedidoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class CompraService {
	@Autowired
	public CompraRepository repository;
	@Autowired
	public PedidoRepository pedidoRepository;
	
	public List<CompraDTO> findAll(){
		List<Compra> compras= repository.findAll();
		List<CompraDTO> obj= new ArrayList<>();
		for(Compra c:compras) {
			CompraDTO dto = new CompraDTO(c);
			List<String> pedidosString= c.getPedidos();
			for(String p: pedidosString) {
				Pedido pedido=pedidoRepository.findOne(p);
				if(pedido==null) {
					throw new ObjectNotFoundException("Objeto não encontrado");  
				}
				dto.insertPedido(pedido);
			}
			obj.add(dto);
		}
		return obj;
	}
	
	public Compra findById(String id) {
		Compra p = repository.findOne(id);
		if(p==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return p;
	}
	
	
	public Compra insert(CompraDTO compraDTO) {
		Compra compra=new Compra(compraDTO);
		//List<String> pedidos= compraDTO.getPedidos();
		
		return repository.insert(compra);
	}
	
	public Compra update(Compra p) {
		Compra obj = findById(p.getId());
		change(obj, p);
		return repository.save(obj);
	}
	
	public void change(Compra p1, Compra p2) {
		p1.setDinheiro(p2.getDinheiro());
		p1.setCartao(p2.getCartao());
		p1.setAbertura(p2.getAbertura());
		p1.setEncerramento(p2.getEncerramento());
		p1.setPedidos(p2.getPedidos());
	}
	
	public void deleteById(String id) {
		repository.delete(id);
	}
}
