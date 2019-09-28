package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.NewCompraInDTO;
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
	
	public Page<Compra> searchCompraByMesa(String mesa, Date minDate, Date maxDate, 
			/*Pageable pageable*/ int size, int page){
		maxDate= new Date(maxDate.getTime()+(24*60*60*1000));
		PageRequest pageRequest = new PageRequest(page, size);
		return compraRepository.searchCompraByMesa(
				mesa, 
				minDate, 
				maxDate,
				(Pageable) pageRequest );
		
	}
		
	public Page<Compra> findAll(){
		int page = 0;
        int size = 10;
        PageRequest pageRequest = new PageRequest(
                page,
                size,
                Sort.Direction.ASC,
                "abertura");
		List<Compra> compras= compraRepository.findAll();
		return new PageImpl<>(compras, 
                pageRequest, size);
	}
	
	public Compra findById(String id) {
		Compra obj  = compraRepository.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return obj;
	}
	
	public Compra insert(NewCompraInDTO compraIn) {
		Compra obj = new Compra(compraIn);
		List<PedidoDTO> pedidos= compraIn.getPedidos();
		for(PedidoDTO pDto : pedidos) {
			Produto produtoAux = new Produto();
			produtoAux.setId(pDto.getProduto());
			Pedido pedido = new Pedido(pDto);
			pedido.setProduto(produtoAux);
			pedido = savePedido(pedido);
			obj.insertPedido(pedido);
		}
		return compraRepository.insert(obj);
	}
	
	public Page<Pedido> findPedidoByCompra(String id){
		List<Pedido> pedidos=findPedidoById(id);
		int page = 0;
        int size = 10;
        PageRequest pageRequest = new PageRequest(
                page,
                size,
                Sort.Direction.ASC,
                "hora");
		return new PageImpl<>(pedidos, pageRequest, size);
	}
	
	public List<Pedido> findPedidoById(String id){
		Compra compra = findById(id);
		if(compra==null) {
			throw new ObjectNotFoundException("Objeto não encontrado");  
		}
		return compra.getPedidos();
	}
	
	public Compra insertNewPedido(String id, List<PedidoDTO> pedidosDTO) {
		Compra compra = findById(id);
		/* insere todos novos pedidos */
		for(PedidoDTO pDTO : pedidosDTO) {
			Produto produtoAux = new Produto();
			produtoAux.setId(pDTO.getProduto());
			
			Pedido pedido = new Pedido(pDTO);
			pedido.setProduto(produtoAux);
			/* salva os novos pedidos no bd*/
			pedido = savePedido(pedido);
			/* adiciona na lista de pedidos da compra*/
			compra.insertPedido(pedido);
		}
		return compraRepository.save(compra);
	}

	public CompraDTO encerrarCompra(String id) {
		Compra compra = findById(id);
		double total=0.0;
		List<Pedido> pedidos= new ArrayList<>();
		for(Pedido p : compra.getPedidos()) {
			pedidos.add(p);
			total+=p.getTotal();
		}
		CompraDTO compraDTO = new CompraDTO(compra);
		compraDTO.setTotal(total);
		compraDTO.setPedidos(pedidos);
		compraDTO.setEncerramento(new Date());
		return compraDTO;
	}
	
	private Pedido savePedido(Pedido pedido) {
		Produto produto= verifyProdutoExist(pedido);
		pedido.setProduto(produto);
		if(pedido.getProduto().isPeso()==true) 
			pedido.setTotal(pedido.getPeso() * pedido.getProduto().getValor() * pedido.getQtdItens());
		else 
			pedido.setTotal(pedido.getProduto().getValor() * pedido.getQtdItens());
		
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
	
	public Compra update(Compra compra) {
		Compra obj = findById(compra.getId());
		change(compra, obj);
		return compraRepository.save(compra);
	}
	
	public void change(Compra compra, Compra obj) {
		if(compra.getDinheiro()==0.0)
			compra.setDinheiro(obj.getDinheiro());
		if(compra.getCartao()==0.0)
			compra.setCartao(obj.getCartao());
		if(compra.getAbertura()==null)
			compra.setAbertura(obj.getAbertura());
		if(compra.getMesa()==null)
			compra.setMesa(obj.getMesa());
		if(compra.getEncerramento()==null)
			compra.setEncerramento(obj.getEncerramento());
		for(Pedido p : obj.getPedidos()) {
			compra.insertPedido(p);
		}
	}
	
	public void deleteById(String id) {
		List<Pedido> pedidos = findPedidoById(id);
		compraRepository.delete(id);
		for(Pedido p : pedidos) {
			pedidoRepository.delete(p.getId());
		}
	}
	
}
