package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.model.Coleta;

public class ColetaRepositoryImpl implements ColetaRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public ColetaRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Coleta> getTrondinColetas() {
		Query query = new Query();
		query.addCriteria(Criteria.where("fornecedor").is("Trondin"));
		List<Coleta> coletas = mongoTemplate.find(query, Coleta.class);
		return coletas;
	}


}
