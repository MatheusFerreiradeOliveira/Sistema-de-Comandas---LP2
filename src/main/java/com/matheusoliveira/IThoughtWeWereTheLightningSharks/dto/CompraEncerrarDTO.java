package com.matheusoliveira.IThoughtWeWereTheLightningSharks.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class CompraEncerrarDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private double dinheiro;
	private double cartao;
	private Date encerramento;
	
	public CompraEncerrarDTO(String id, double dinheiro, double cartao, Date encerramento) {
		super();
		this.id = id;
		this.dinheiro = dinheiro;
		this.cartao = cartao;
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
