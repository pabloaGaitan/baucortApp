package com.baucort.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombresApellidosAcudiente;
	private String telefonoAcudiente;
	private String correoAcudiente;
	private String direccion;
	private String nombresApellidosAcudiente2;
	private String telefonoAcudiente2;
	private String correoAcudiente2;
	private String direccion2;
	private String nombresApellidos;
	@ManyToOne
	@JoinColumn(name = "curso")
	private Curso curso;
	private String grupo;
	@Column(unique = true)
	private String codigo;
	private Long saldoAlmuerzo;
	private Long saldoLonchera;
	private Boolean esDeLonchera;
	private Boolean esDeAlmuerzo;
	private Double descuento;
	private String descuentoMotivo;
	
	@OneToMany(mappedBy = "estudiante")
	private List<Pago> pagos;
	
	@ManyToMany
	private List<Asistencia> asistencias;
	
	@ManyToMany
	private List<Inasistencia> inasistencia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombresApellidosAcudiente() {
		return nombresApellidosAcudiente;
	}
	public void setNombresApellidosAcudiente(String nombresApellidosAcudiente) {
		this.nombresApellidosAcudiente = nombresApellidosAcudiente;
	}
	public String getTelefonoAcudiente() {
		return telefonoAcudiente;
	}
	public void setTelefonoAcudiente(String telefonoAcudiente) {
		this.telefonoAcudiente = telefonoAcudiente;
	}
	public String getCorreoAcudiente() {
		return correoAcudiente;
	}
	public void setCorreoAcudiente(String correoAcudiente) {
		this.correoAcudiente = correoAcudiente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombresApellidosAcudiente2() {
		return nombresApellidosAcudiente2;
	}
	public void setNombresApellidosAcudiente2(String nombresApellidosAcudiente2) {
		this.nombresApellidosAcudiente2 = nombresApellidosAcudiente2;
	}
	public String getTelefonoAcudiente2() {
		return telefonoAcudiente2;
	}
	public void setTelefonoAcudiente2(String telefonoAcudiente2) {
		this.telefonoAcudiente2 = telefonoAcudiente2;
	}
	public String getCorreoAcudiente2() {
		return correoAcudiente2;
	}
	public void setCorreoAcudiente2(String correoAcudiente2) {
		this.correoAcudiente2 = correoAcudiente2;
	}
	public String getDireccion2() {
		return direccion2;
	}
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}
	public String getNombresApellidos() {
		return nombresApellidos;
	}
	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getSaldoAlmuerzo() {
		return saldoAlmuerzo;
	}
	public void setSaldoAlmuerzo(Long saldoAlmuerzo) {
		this.saldoAlmuerzo = saldoAlmuerzo;
	}
	public Long getSaldoLonchera() {
		return saldoLonchera;
	}
	public void setSaldoLonchera(Long saldoLonchera) {
		this.saldoLonchera = saldoLonchera;
	}
	public Boolean getEsDeLonchera() {
		return esDeLonchera;
	}
	public void setEsDeLonchera(Boolean esDeLonchera) {
		this.esDeLonchera = esDeLonchera;
	}
	public Boolean getEsDeAlmuerzo() {
		return esDeAlmuerzo;
	}
	public void setEsDeAlmuerzo(Boolean esDeAlmuerzo) {
		this.esDeAlmuerzo = esDeAlmuerzo;
	}
	public List<Pago> getPagos() {
		return pagos;
	}
	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}
	public List<Inasistencia> getInasistencia() {
		return inasistencia;
	}
	public void setInasistencia(List<Inasistencia> inasistencia) {
		this.inasistencia = inasistencia;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}	
	
	public String getDescuentoMotivo() {
		return descuentoMotivo;
	}
	public void setDescuentoMotivo(String descuentoMotivo) {
		this.descuentoMotivo = descuentoMotivo;
	}
	
	public void addAsistencia(Asistencia asistencia) {
		this.asistencias.add(asistencia);
	}

}
