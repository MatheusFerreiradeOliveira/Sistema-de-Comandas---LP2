package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.CompraRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class CompraService {
	@Autowired
	public CompraRepository repository;
	
	public List<Compra> findAll(){
		return repository.findAll();
	}
	
	public Compra findById(String id) {
		return repository.findOne(id);
	}
	
	public Compra getOne(String id) {
		Compra p = repository.findOne(id);
		if(p==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return p;
	}
	
	public Compra insert(Compra p) {
		return repository.insert(p);
	}
	
	public Compra update(Compra p) {
		Compra obj = getOne(p.getId());
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
