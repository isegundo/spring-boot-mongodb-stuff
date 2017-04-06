package com.example.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Coleta;
import com.example.repository.ColetaRepository;
import com.mongodb.MongoInternalException;

@RestController
@RequestMapping("/coleta")
public class ColetaController {
	
	@Autowired
	private ColetaRepository repository;
	
	@GetMapping
	public List<Coleta> getAll()
	{
	
		return repository.findAll();
	}
	
	@PostMapping
	public Coleta create(String fornecedor, String cidade, String checkIn)
	{
		
		Coleta c = new Coleta();
		c.setCheckin(LocalDate.parse(checkIn, DateTimeFormatter.ISO_DATE));
		c.setCidade(cidade);
		c.setFornecedor(fornecedor);
		return repository.save(c);
	}
	
	
	@PutMapping("/delete")
	public void delete(String fornecedor, String cidade, String checkIn)
	{
		
		Long c = repository.deleteBy(LocalDate.parse(checkIn, DateTimeFormatter.ISO_DATE), fornecedor, cidade);
		System.out.println(c);
	}
	
	@GetMapping("/{checkIn}")
	public Long getCountExcluding(@PathVariable String checkIn)
	{
		return repository.countNotTrondNorTrondin(LocalDate.parse(checkIn, DateTimeFormatter.ISO_DATE));
	}
	
	
	
}
