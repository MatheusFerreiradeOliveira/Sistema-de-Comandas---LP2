package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;

public interface CompraRepository extends MongoRepository<Compra, String> {
	
}
