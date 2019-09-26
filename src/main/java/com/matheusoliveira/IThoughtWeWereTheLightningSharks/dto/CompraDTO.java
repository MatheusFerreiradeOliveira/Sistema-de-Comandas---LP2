package com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

public class CompraDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Date abertura;
	private Date encerramento;
	private double total;
	@DBRef
	private List<Pedido> pedidos = new ArrayList<>();

	public CompraDTO() {};
	
	public CompraDTO(String id, Date abertura, Date encerramento, 
			double total,  List<Pedido> pedidos) {
		super();
		this.id = id;
		this.abertura = abertura;
		this.encerramento = encerramento;
		this.total = total;
		this.pedidos = pedidos;
	}
	
	public CompraDTO(Compra compra) {
		super();
		this.id = compra.getId();
		this.abertura = compra.getAbertura();
		this.encerramento = compra.getEncerramento();
		this.total = compra.getCartao()+compra.getDinheiro();
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

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
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
	public String toString() {
		return "CompraDTO [id=" + id + ", abertura=" + abertura + ", encerramento=" + encerramento + ", total="
				+ total + ", pedidos=" + pedidos + "]";
	}
	
	
}
