package com.matheusoliveira.IThoughtWeWereTheLightningSharks.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

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
		produtoRepository.deleteAll();
		pedidoRepository.deleteAll();
		compraRepository.deleteAll();
		
		Produto p1 = new Produto(null, "Peixe frito", (35.0), 50, true);
		Produto p2 = new Produto(null, "Batata frita", (15.0), 10, false);
		Produto p3 = new Produto(null, "Carne de Sol", (20.0), 20, true);
		Produto p4 = new Produto(null, "Cerveja", (3.50),100, false);
		Produto p5 = new Produto(null, "Lombinho do Micaias", (0.05),1, false);
		//List<Produto> produto= new ArrayList<Produto>();
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4,p5));
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		sdf1.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:MM");
		sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Pedido ped1 = new Pedido(null, 2, (float)5.50, sdf1.parse("12/09/2019"), "jajaja", p1);
		
		Pedido ped2 = new Pedido(null, 2, (float)5.50, sdf1.parse("12/09/2019"), "Holamanito", p2);
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		
		List<Pedido> l = new ArrayList<>();
		
		l.add(ped1);
		l.add(ped2);
		
		Compra c1 = new Compra(null, sdf2.parse("21:16"), sdf2.parse("21:59"), (float)5.50, (float)9.50, l);

		compraRepository.save(Arrays.asList(c1));
		
	}

}
