package com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Compra;

public class CompraEncerrarDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Date encerramento;
	private double cartao;
	private double dinheiro;
	public CompraEncerrarDTO() {}
	public CompraEncerrarDTO(String id, double dinheiro, double cartao, Date encerramento) {
		super();
		this.id = id;
		this.dinheiro = dinheiro;
		this.cartao = cartao;
		this.encerramento=encerramento;
	}
	public CompraEncerrarDTO(Compra compra) {
		super();
		this.id = compra.getId();
		this.encerramento = compra.getEncerramento();
		this.dinheiro = compra.getDinheiro();
		this.cartao = compra.getCartao();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getDinheiro() {
		return dinheiro;
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
	public Date getEncerramento() {
		return encerramento;
	}
	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}
	@Override
	public String toString() {
		return "CompraEncerrarDto [id=" + id + ", dinheiro=" + dinheiro 
				+ ", cartao=" + cartao+ ", encerramento=" + encerramento + "]";
	}	
}
