package com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

public class CompraDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Date abertura;
	private Date encerramento;
	private double cartao;
	private double dinheiro;
	@DBRef
	private List<Pedido> pedidos = new ArrayList<>();

	public CompraDTO() {};
	
	public CompraDTO(String id, Date abertura, Date encerramento, double cartao, Double dinheiro,
			List<Pedido> pedidos) {
		super();
		this.id = id;
		this.abertura = abertura;
		this.encerramento = encerramento;
		this.cartao = cartao;
		this.dinheiro = dinheiro;
		this.pedidos = pedidos;
	}
	
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

	public double getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(double dinheiro) {
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

	
}
