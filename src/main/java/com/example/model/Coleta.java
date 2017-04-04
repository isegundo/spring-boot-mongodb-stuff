package com.example.model;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Coleta {
	
	@Id
	private BigInteger id;
	
	private LocalDate checkin;
	
	private String fornecedor;
	
	private String cidade;

	
	public Coleta()
	{}
	
	
	public Coleta(String fornecedor, String cidade, LocalDate checkin) {
		super();
		this.checkin = checkin;
		this.fornecedor = fornecedor;
		this.cidade = cidade;
	}



	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	

}
