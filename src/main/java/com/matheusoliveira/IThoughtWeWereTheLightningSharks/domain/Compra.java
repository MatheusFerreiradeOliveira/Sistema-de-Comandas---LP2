package com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto.CompraDTO;

@Document(collection="compras")
public class Compra {
	@Id
	private String id;
	private Date abertura;
	private Date encerramento;
	private String mesa;
	private double cartao;
	private double dinheiro;
	@DBRef(lazy=true)
	private List<Pedido> pedidos = new ArrayList<>();

	public Compra() {};
	
	public Compra(String id, Date abertura, Date encerramento,String mesa, double cartao, Double dinheiro,
			List<Pedido> pedidos) {
		super();
		this.id = id;
		this.abertura = abertura;
		this.encerramento = encerramento;
		this.mesa=mesa;
		this.cartao = cartao;
		this.dinheiro = dinheiro;
		this.pedidos = pedidos;
	}
	public Compra(CompraDTO compra) {
		super();
		this.id = compra.getId();
		this.abertura = compra.getAbertura();
		this.encerramento = compra.getEncerramento();
		this.cartao = compra.getCartao();
		this.dinheiro = compra.getDinheiro();
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
	
	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
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
	public void insertAllPedido(List<Pedido> p) {
		pedidos.addAll(p);
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

	@Override
	public String toString() {
		return "Compra [id=" + id + ", abertura=" + abertura + ", encerramento=" + encerramento + ", cartao=" + cartao
				+ ", dinheiro=" + dinheiro + ", pedidos=" + pedidos + "]";
	}
}
