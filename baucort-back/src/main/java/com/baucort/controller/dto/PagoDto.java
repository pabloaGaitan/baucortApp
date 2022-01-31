package com.baucort.controller.dto;

import java.time.LocalDate;

import com.baucort.entities.Tipo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PagoDto {
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fecha;
	private Tipo tipo;
	private String numero;
	private Long valor;
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	

}
