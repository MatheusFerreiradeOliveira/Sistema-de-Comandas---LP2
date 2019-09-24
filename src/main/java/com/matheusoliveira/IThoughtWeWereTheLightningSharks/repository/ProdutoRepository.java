package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{

}
