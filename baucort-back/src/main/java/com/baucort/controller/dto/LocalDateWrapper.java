package com.baucort.controller.dto;

import java.time.LocalDate;

public class LocalDateWrapper {
	
	private LocalDate fecha;
	
	public LocalDateWrapper() {
		this.fecha = LocalDate.now();
	}
	
	public LocalDateWrapper(int dia, int mes, int anio) {
		this.fecha = LocalDate.of(anio, mes, dia);
	}
	
	public void sumarDia() {
		this.fecha = this.fecha.plusDays(1);
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
}
