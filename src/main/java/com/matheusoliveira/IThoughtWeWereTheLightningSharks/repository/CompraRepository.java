package com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;

@Repository
public interface CompraRepository extends MongoRepository<Compra, String> {
	
	/*
	@Query("{ 'mesa' : {$regex: ?0, $options: 'i'}}")
	List<Compra> searchByMesa(String mesa);
	*/
	
	@Query("{ $and :[ "
			+ "{'mesa' : {$regex: ?0, $options: 'i'}},"
			+ "{'abertura' : { $gte: ?1}},"
			+ "{'encerramento' : { $lte: ?2}} "
			+ "] }")
	Page<Compra> searchCompraByMesa(String mesa, Date minDate, Date maxDate, Pageable pageRequest);
	
}
