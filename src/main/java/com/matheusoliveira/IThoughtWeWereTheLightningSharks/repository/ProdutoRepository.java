package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String>{

}
