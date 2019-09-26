package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {

	Page<Pedido> findByObsContainingIgnoreCase(String obs, Pageable pageRequest);
	
	@Query("{ $and :[ "
			+ "{'produto.nome' : {$regex: ?0, $options: 'i'}},"
			+ "{'hora' : { $gte: ?1}},"
			+ "{'hora' : { $lte: ?2}} "
			+ "] }")
	Page<Pedido> searchPedido(String produto, Date minDate, Date maxDate, Pageable pageRequest);
}
