package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Usuario;
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
}
