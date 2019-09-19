package com.matheusoliveira.IThoughtWeWereTheLightningSharks.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.CompraRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PedidoRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public PedidoRepository pedidoRepository;
	
	@Autowired
	public CompraRepository compraRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		produtoRepository.deleteAll();
		compraRepository.deleteAll();
		pedidoRepository.deleteAll();
		Produto p1 = new Produto(null, "Peixe frito", 35.0, true);
		Produto p2 = new Produto(null, "Batata frita", 15.0, false);
		Produto p3 = new Produto(null, "Carne de Sol", 20.0, true);
		Produto p4 = new Produto(null, "Cerveja", 3.50, false);
		Produto p5 = new Produto(null, "Lombinho do Micaias", 0.05, false);
		//List<Produto> produto= new ArrayList<Produto>();
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4,p5));
		Pedido pedido1=new Pedido(null, 1, 35.0, 1.0, sdf.parse("2019-07-20 12:30:00"), "sem espinas", p1);
		Pedido pedido2=new Pedido(null, 2, 7.0, 0, sdf.parse("2019-07-20 12:45:00"), "", p4);
		pedidoRepository.save(Arrays.asList(pedido1,pedido2));
		Compra c1=new Compra(null, sdf.parse("2019-07-20 12:30:00"),
				sdf.parse("2019-07-20 16:45:00"), 0.0, 43.0, Arrays.asList(pedido1,pedido2) );
		compraRepository.save(c1);
		
	}

}
