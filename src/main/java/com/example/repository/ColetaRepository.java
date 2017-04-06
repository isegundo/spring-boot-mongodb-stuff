package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.model.Coleta;

public interface ColetaRepository extends  MongoRepository<Coleta, Long>, ColetaRepositoryCustom {
	
	public List<Coleta> findByCidade(String cidade);
	public List<Coleta> findByFornecedor(String fornecedor);
	public List<Coleta> findByCheckin(LocalDate checkin);
	
	@Query(value="{'checkin': ?0, 'fornecedor': ?1, 'cidade': ?2}", delete=true)
	public Long deleteBy(LocalDate checkin, String fornecedor, String cidade);
	
	@Query(value = "{ 'fornecedor' : { $nin: ['Trend', 'TrendFlash'] } }", count = true)
	public Long countNotTrondNorTrondin();
	
	@Query(value="{'checkin': ?0, 'fornecedor':{$nin: ['Trond','Trondin']}}", count = true)
    public Long countNotTrondNorTrondin(LocalDate date);

}
