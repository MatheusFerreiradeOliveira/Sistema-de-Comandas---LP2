package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.PedidoDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PedidoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public Pedido findById(String id) {
		Pedido p = pedidoRepository.findOne(id);
		if(p==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return p;
	}
		
	public Pedido insert(PedidoDTO p) {
		Pedido pedido= new Pedido(p);
		if(p.getProduto()!=null) {
			Produto produto= produtoRepository.findOne(p.getProduto());
			if(produto == null) {
				throw new ObjectNotFoundException("Objeto não encontrado"); 
			}
			pedido.setProduto(produto);
		}else {
			throw new ObjectNotFoundException("Objeto não encontrado: Não existe produto"); 
		}
		return pedidoRepository.insert(pedido);
	}
	
	public Pedido update(PedidoDTO p) {
		Pedido obj = findById(p.getId());
		change(obj, p);
		return pedidoRepository.save(obj);
	}
	
	public void change(Pedido p, PedidoDTO pDTO) {
		if(pDTO.getHora()!=null)
			p.setHora(pDTO.getHora());
		if(pDTO.getObs()!=null)
			p.setObs(pDTO.getObs());
		if(pDTO.getQtdItens()!= 0)
			p.setQtdItens(pDTO.getQtdItens());
		if(pDTO.getTotal()!= 0)
			p.setTotal(pDTO.getTotal());
		Produto produto;
		if(pDTO.getProduto()!=null) {
			produto = produtoRepository.findOne(pDTO.getProduto());
			if(produto == null) {
				throw new ObjectNotFoundException("Objeto não encontrado"); 
			}
		}else {
			throw new ObjectNotFoundException("Objeto não encontrado: Não existe produto");
		}
		p.setProduto(produto);
	}
	
	public void deleteById(String id) {
		pedidoRepository.delete(id);
	}
}
