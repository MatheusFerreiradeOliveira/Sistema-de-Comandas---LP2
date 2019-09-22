package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.CompraRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PedidoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class CompraService {
	@Autowired
	public CompraRepository compraRepository;
	@Autowired
	public PedidoRepository pedidoRepository;
	@Autowired
	public ProdutoRepository produtoRepository;
	
	public List<CompraDTO> findAll(){
		List<Compra> compras= compraRepository.findAll();
		List<CompraDTO> obj= new ArrayList<>();
		for(Compra c : compras) {
			CompraDTO dto = new CompraDTO(c);
			List<String> pedidosString= c.getPedidos();
			for(String pId : pedidosString) {
				Pedido pedido=pedidoRepository.findOne(pId);
				dto.insertPedido(pedido);
			}
			obj.add(dto);
		}
		return obj;
	}
	
	public CompraDTO findById(String id) {
		Compra obj  = compraRepository.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		CompraDTO dto = new CompraDTO(obj);
		List<String> pedidos= obj.getPedidos();
		for(String pId : pedidos) {
			Pedido pedido = pedidoRepository.findOne(pId);
			dto.insertPedido(pedido);
		}
		return dto;
	}
	
	
	public Compra insert(CompraDTO compraDTO) {
		Compra compra=new Compra(compraDTO);
		List<Pedido> pedidos= compraDTO.getPedidos();
		for(Pedido pedido : pedidos) {
			/** caso nao tenha produto, caso ele nao exista, caso os valores sejam difentes*/
			if(pedido.getProduto()== null) {
				throw new ObjectNotFoundException("Objeto não encontrado: Não existe produto"); 
			}
			String produtoId= pedido.getProduto().getId();
			Produto produto = produtoRepository.findOne(produtoId);
			if(produto == null) {
				throw new ObjectNotFoundException("Objeto não encontrado"); 
			}/* acho que nao é necessario este if */
			if(pedido.getProduto().getNome() != null) {
				if(pedido.getProduto().getNome().compareTo(produto.getNome())!=0) {
					System.out.println(pedido.getProduto().getNome()+" \n"+produto.getNome()+"\n"+
							pedido.getProduto().getNome().compareTo(produto.getNome()));
					throw new ObjectNotFoundException("Produto não se refere ao encontrado no BD"); 
				}
			}
			pedido.setProduto(produto);
			pedido = pedidoRepository.insert(pedido);
			compra.insertPedido(pedido.getId());
		}
		return compraRepository.insert(compra);
	}
	/*
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
	*/
}
