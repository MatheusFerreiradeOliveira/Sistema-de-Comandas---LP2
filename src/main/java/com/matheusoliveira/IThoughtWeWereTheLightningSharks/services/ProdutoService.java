package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	public ProdutoRepository repository;
	
	public List<Produto> findAll(){
		return repository.findAll();
	}
	
	public Produto findById(String id) {
		return repository.findOne(id);
	}
	
	public Produto getOne(String id) {
		Produto p = repository.findOne(id);
		if(p==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return p;
	}
	
	public Produto insert(Produto p) {
		return repository.insert(p);
	}
	
	public Produto update(Produto p) {
		Produto obj = getOne(p.getId());
		change(obj, p);
		return repository.save(obj);
	}
	
	public void change(Produto p1, Produto p2) {
		p1.setNome(p2.getNome());
		p1.setPeso(p2.isPeso());
		p1.setQtd(p2.getQtd());
		p1.setValor(p2.getValor());
	}
	
	public void deleteById(String id) {
		repository.delete(id);
	}
}
