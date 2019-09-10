package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PedidoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	public PedidoRepository repository;
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	public Pedido findById(String id) {
		return repository.findOne(id);
	}
	
	public Pedido getOne(String id) {
		Pedido p = repository.findOne(id);
		if(p==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return p;
	}
	
	public Pedido insert(Pedido p) {
		return repository.insert(p);
	}
	
	public Pedido update(Pedido p) {
		Pedido obj = getOne(p.getId());
		change(obj, p);
		return repository.save(obj);
	}
	
	public void change(Pedido p1, Pedido p2) {
		p1.setHora(p2.getHora());
		p1.setObs(p2.getObs());
		p1.setProduto(p2.getProduto());
		p1.setQtdItens(p2.getQtdItens());
		p1.setValor(p2.getValor());
	}
	
	public void deleteById(String id) {
		repository.delete(id);
	}
}
