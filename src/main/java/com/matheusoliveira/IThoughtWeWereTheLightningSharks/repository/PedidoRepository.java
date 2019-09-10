package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

}
