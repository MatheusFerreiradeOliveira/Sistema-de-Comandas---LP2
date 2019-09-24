package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {

	List<Pedido> findByObsContainingIgnoreCase(String obs);
	
	@Query("{ 'mesa' : {$regex: ?0, $options: 'i'}}")
	List<Compra> searchCompraByMesa(Date minDate, Date maxDate);
}
