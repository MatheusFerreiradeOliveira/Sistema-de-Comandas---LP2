package com.matheusoliveira.IThoughtWeWereTheLightningSharks.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.ProdutoRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		produtoRepository.deleteAll();
		Produto p1 = new Produto(null, "Peixe frito", (35.0),50, true);
		Produto p2 = new Produto(null, "Batata frita", (15.0),10, false);
		Produto p3 = new Produto(null, "Carne de Sol", (20.0),20, true);
		Produto p4 = new Produto(null, "Cerveja", (3.50),100, false);
		Produto p5 = new Produto(null, "Lombinho do Micaias", (0.05),1, false);
		//List<Produto> produto= new ArrayList<Produto>();
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4,p5));
		
		
	}

}
