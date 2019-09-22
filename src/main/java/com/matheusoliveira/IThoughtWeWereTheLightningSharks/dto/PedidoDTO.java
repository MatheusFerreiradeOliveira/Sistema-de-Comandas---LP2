package com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Pedido;

public class PedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private int qtdItens;
	private double total;
	private double peso;
	private Date hora;
	private String obs;
	private String produto;
	
	PedidoDTO(){}
	public PedidoDTO(String id, int qtdItens, double total,
			double peso, Date hora, String obs, String produto) {
		super();
		this.id = id;
		this.qtdItens = qtdItens;
		this.total = total;
		this.peso = peso;
		this.hora = hora;
		this.obs = obs;
		this.produto = produto;
	}
	public PedidoDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.qtdItens = pedido.getQtdItens();
		this.total = pedido.getTotal();
		this.peso = pedido.getPeso();
		this.hora = pedido.getHora();
		this.obs = pedido.getObs();
		this.produto = pedido.getProduto().getId();
	}
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
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
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	@Override
	public String toString() {
		return "PedidoDTO [id=" + id + ", qtdItens=" + qtdItens + ", total=" + total + ", peso=" + peso + ", hora="
				+ hora + ", obs=" + obs + ", produto=" + produto + "]";
	}
	

}
