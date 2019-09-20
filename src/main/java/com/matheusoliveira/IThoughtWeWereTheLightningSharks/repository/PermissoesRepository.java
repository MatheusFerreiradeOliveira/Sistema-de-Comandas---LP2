package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Permissoes;

public interface PermissoesRepository extends MongoRepository<Permissoes, String> {
	
}
