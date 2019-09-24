package com.matheusoliveira.IThoughtWeWereTheLightningSharks.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Permissoes;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Usuario;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.CompraRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PedidoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PermissoesRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.UsuarioRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public PedidoRepository pedidoRepository;
	
	@Autowired
	public CompraRepository compraRepository;
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Autowired
	public PermissoesRepository permissoesRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		produtoRepository.deleteAll();
		compraRepository.deleteAll();
		pedidoRepository.deleteAll();
		usuarioRepository.deleteAll();
		permissoesRepository.deleteAll();
		Produto p1 = new Produto(null, "Peixe frito", 35.0, true);
		Produto p2 = new Produto(null, "Batata frita", 15.0, false);
		Produto p3 = new Produto(null, "Carne de Sol", 20.0, true);
		Produto p4 = new Produto(null, "Cerveja", 3.50, false);
		Produto p5 = new Produto(null, "Lombinho do Micaias", 0.05, false);
		//List<Produto> produto= new ArrayList<Produto>();
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4,p5));
		Pedido pedido1=new Pedido(null, 1, 35.0, 1.0, sdf.parse("2019-07-20 12:30:00"), "sem espinas", p1);
		Pedido pedido2=new Pedido(null, 2, 7.0, 0, sdf.parse("2019-07-20 12:45:00"), "", p4);
		//pedidoRepository.save(Arrays.asList(pedido1,pedido2));
		pedido1 = pedidoRepository.save(pedido1);
		pedido2 = pedidoRepository.save(pedido2);
		List<String> pedidosSalvos= new ArrayList<>();
		pedidosSalvos.add(pedido1.getId());
		pedidosSalvos.add(pedido2.getId());
		
		Compra c1=new Compra(null, 
				sdf.parse("2019-07-20 12:30:00"),
				sdf.parse("2019-07-20 16:45:00"), 
				"A",
				0.0, 
				43.0, 
				pedidosSalvos
				);
		Compra c2=new Compra(null, 
				sdf.parse("2019-07-20 12:30:00"),
				sdf.parse("2019-07-20 16:45:00"), 
				"B",
				0.0, 
				43.0, 
				pedidosSalvos
				);
		compraRepository.save(c1);
		compraRepository.save(c2);
		
		//Lista de permissoes
		
		Permissoes per1 = new Permissoes(null, "Adicionar mesas");
		Permissoes per2 = new Permissoes(null, "Inserir produto");
		Permissoes per3 = new Permissoes(null, "Realizar pedido");
		Permissoes per4 = new Permissoes(null, "Listar compras");
		
		permissoesRepository.save(Arrays.asList(per1, per2, per3, per4));
		
		//Listar users
		
		Usuario user1 = new Usuario(null, "Joaquin", Arrays.asList(per1, per2, per3, per4));
		Usuario user2 = new Usuario(null, "Garçoneide", Arrays.asList(per3));
		
		usuarioRepository.save(Arrays.asList(user1, user2));
		
		
	}

}
