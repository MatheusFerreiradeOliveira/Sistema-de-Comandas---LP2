package com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="pedido")
public class Pedido {
	@Id
	private String id;
	private int qtdItens;
	private double valorTotal;
	private double peso;
	private Date hora;
	private String obs;
	private Produto produto;

	public Pedido() {};
	
	public Pedido(String id, int qtdItens, double valorTotal, double peso, Date hora, String obs, Produto produto) {
		super();
		this.id = id;
		this.qtdItens = qtdItens;
		this.peso = peso;
		this.valorTotal = valorTotal;
		this.hora = hora;
		this.obs = obs;
		this.produto = produto;
	}
	/*getters and setters*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getvalorTotal() {
		return valorTotal;
	}
	public void setvalorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
