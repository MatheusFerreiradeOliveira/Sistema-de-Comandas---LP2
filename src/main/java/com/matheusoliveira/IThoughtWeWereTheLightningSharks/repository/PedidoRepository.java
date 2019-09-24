package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {

	List<Pedido> findByObsContainingIgnoreCase(String obs);
	
	@Query("{ $and :[ "
			+ "{'produto.nome' : {$regex: ?0, $options: 'i'}},"
			+ "{'hora' : { $gte: ?1}},"
			+ "{'hora' : { $lte: ?2}} "
			+ "] }")
	List<Pedido> searchPedido(String produto, Date minDate, Date maxDate);
}
