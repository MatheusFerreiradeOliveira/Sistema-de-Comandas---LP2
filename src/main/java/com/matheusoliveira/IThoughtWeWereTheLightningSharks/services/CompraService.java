package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.PedidoDTO;
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
			pedido = savePedido(pedido);
			compra.insertPedido(pedido.getId());
		}
		return compraRepository.insert(compra);
	}
	
	public List<Pedido> findPedidoById(String id){
		CompraDTO compraDTO = findById(id);
		return compraDTO.getPedidos();
	}
	
	public Compra insertNewPedido(String id, List<PedidoDTO> pedidosDTO) {
		CompraDTO compraDTO = findById(id);
		Compra compra = new Compra(compraDTO);
		/* insere todos pedidos ja existentes */
		for(Pedido p : compraDTO.getPedidos()) {
			compra.insertPedido(p.getId());
		}
		/* insere todos novos pedidos */
		for(PedidoDTO pDTO : pedidosDTO) {
			/* usa produto aux pra procurar se é valido. obs: ele faz isso no momento de inserir*/
			Produto produtoAux = new Produto();
			produtoAux.setId(pDTO.getProduto());
			Pedido pedido = new Pedido(pDTO);
			pedido.setProduto(produtoAux);
			/* salva os novos pedidos no bd*/
			pedido = savePedido(pedido);
			/* adiciona na lista de pedidos da compra*/
			compra.insertPedido(pedido.getId());
		}
		return compraRepository.save(compra);
	}
	private Pedido savePedido(Pedido pedido) {
		Produto produto= verifyProdutoExist(pedido);
		pedido.setProduto(produto);
		return pedidoRepository.insert(pedido);
	}
	
	private Produto verifyProdutoExist(Pedido pedido) {
		/** caso nao tenha produto, caso ele nao exista, caso os valores sejam difentes*/
		if(pedido.getProduto()== null) {
			throw new ObjectNotFoundException("Objeto não encontrado: Não existe produto"); 
		}
		String produtoId= pedido.getProduto().getId();
		Produto produto = produtoRepository.findOne(produtoId);
		if(produto == null) {
			throw new ObjectNotFoundException("Objeto não encontrado"); 
		}/* verifica se os dados que ele passar for diferente do encontrado no banco*/
		if(pedido.getProduto().getNome() != null) {
			if(pedido.getProduto().getNome().compareTo(produto.getNome())!=0) {
				System.out.println(pedido.getProduto().getNome()+" \n"+produto.getNome()+"\n"+
						pedido.getProduto().getNome().compareTo(produto.getNome()));
				throw new ObjectNotFoundException("Produto não se refere ao encontrado no BD"); 
			}
		}
		return produto;
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
