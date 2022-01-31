package com.baucort.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String curso;
	private Long valorAlmuerzo;
	private Long valorLonchera;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Long getValorAlmuerzo() {
		return valorAlmuerzo;
	}
	public void setValorAlmuerzo(Long valorAlmuerzo) {
		this.valorAlmuerzo = valorAlmuerzo;
	}
	public Long getValorLonchera() {
		return valorLonchera;
	}
	public void setValorLonchera(Long valorLonchera) {
		this.valorLonchera = valorLonchera;
	}
	
	
}
