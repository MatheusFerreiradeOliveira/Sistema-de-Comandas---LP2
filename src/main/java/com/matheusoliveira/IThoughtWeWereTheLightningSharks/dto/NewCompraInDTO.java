package com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;

public class NewCompraInDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Date abertura;
	private String mesa;
	@DBRef
	private List<PedidoDTO> pedidos = new ArrayList<>();

	public NewCompraInDTO() {};
	
	public NewCompraInDTO(String id, Date abertura, String mesa, List<PedidoDTO> pedidos) {
		super();
		this.id = id;
		this.abertura = abertura;
		this.mesa = mesa;
		this.pedidos = pedidos;
	}
	
	public NewCompraInDTO(Compra compra) {
		super();
		this.id = compra.getId();
		this.abertura = compra.getAbertura();
		this.mesa = compra.getMesa();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMesa() {
		return mesa;
	}
	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public Date getAbertura() {
		return abertura;
	}
	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}
	public void insertPedido(PedidoDTO p) {
		this.pedidos.add(p);
	}
	public void insertAllPedido(List<PedidoDTO> p) {
		pedidos.addAll(p);
	}

	@Override
	public String toString() {
		return "NewCompraInDto [id=" + id + ", abertura=" + abertura  +", pedidos=" + pedidos + "]";
	}
	
	
}
