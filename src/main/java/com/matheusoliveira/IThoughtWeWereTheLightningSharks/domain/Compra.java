package com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Compras")
public class Compra {
	@Id
	private String id;
	private Date abertura;
	private Date encerramento;
	private double cartao;
	private double dinheiro;
	@DBRef
	private List<Pedido> pedidos = new ArrayList<>();

	public Compra() {};

	public Compra(String id, Date abertura, Date encerramento, double cartao, Double dinheiro,
			List<Pedido> pedidos) {
		super();
		this.id = id;
		this.abertura = abertura;
		this.encerramento = encerramento;
		this.cartao = cartao;
		this.dinheiro = dinheiro;
		this.pedidos = pedidos;
	}
	
	/*getters and setters*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getAbertura() {
		return abertura;
	}
	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}
	public Date getEncerramento() {
		return encerramento;
	}
	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}
	public double getCartao() {
		return cartao;
	}
	public void setCartao(double cartao) {
		this.cartao = cartao;
	}
	public Double getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(Double dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void insertPedido(Pedido p) {
		this.pedidos.add(p);
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
		Compra other = (Compra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
