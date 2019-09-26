package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	public ProdutoRepository repository;
	
	public Page<Produto> findAll(int page, int size){
        PageRequest pageRequest = new PageRequest(
                page,
                size,
                Sort.Direction.ASC,
                "abertura");
		List<Produto> p= repository.findAll();
		return new PageImpl<>(p, 
                pageRequest, size);
	}
	
	public Produto findById(String id) {
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
		Produto obj = findById(p.getId());
		change(obj, p);
		return repository.save(obj);
	}
	
	public void change(Produto p1, Produto p2) {
		p1.setNome(p2.getNome());
		p1.setPeso(p2.isPeso());
		p1.setValor(p2.getValor());
	}
	
	public void deleteById(String id) {
		repository.delete(id);
	}
}
