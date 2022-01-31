package com.baucort.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FechaDto {
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fecha;
	
	public FechaDto() {
		
	}
	
	public FechaDto(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
