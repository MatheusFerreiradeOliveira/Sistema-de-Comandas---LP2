package com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="produtos")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String nome;
	private Double valor;
	private boolean peso;
	
	public Produto() {}
	
	public Produto(String id, String nome, Double valor,  boolean peso) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.peso = peso;
	}
	/*getters and setters*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public boolean isPeso() {
		return peso;
	}
	public void setPeso(boolean peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
