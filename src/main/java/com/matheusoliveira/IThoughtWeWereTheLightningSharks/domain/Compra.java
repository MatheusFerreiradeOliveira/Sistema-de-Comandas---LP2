package com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Compra {
	@Id
	String id;
	//int mesa;
	Date horaabertura;
	Date horaencerramento;
	float cartao;
	float bolsonaros;
	
	List<Pedido> pedidos = new ArrayList<>();

	public Compra() {};

	public Compra(String id, Date horaabertura, Date horaencerramento, float cartao, float bolsonaros,
			List<Pedido> pedidos) {
		super();
		this.id = id;
		this.horaabertura = horaabertura;
		this.horaencerramento = horaencerramento;
		this.cartao = cartao;
		this.bolsonaros = bolsonaros;
		this.pedidos = pedidos;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getHoraabertura() {
		return horaabertura;
	}
	public void setHoraabertura(Date horaabertura) {
		this.horaabertura = horaabertura;
	}
	public Date getHoraencerramento() {
		return horaencerramento;
	}
	public void setHoraencerramento(Date horaencerramento) {
		this.horaencerramento = horaencerramento;
	}
	public float getCartao() {
		return cartao;
	}
	public void setCartao(float cartao) {
		this.cartao = cartao;
	}
	public float getBolsonaros() {
		return bolsonaros;
	}
	public void setBolsonaros(float bolsonaros) {
		this.bolsonaros = bolsonaros;
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
