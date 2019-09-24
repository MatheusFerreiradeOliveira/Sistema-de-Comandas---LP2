package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Permissoes;

@Repository
public interface PermissoesRepository extends MongoRepository<Permissoes, String> {
	
}
