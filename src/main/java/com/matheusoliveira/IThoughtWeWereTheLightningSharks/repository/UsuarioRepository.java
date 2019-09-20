package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
}
