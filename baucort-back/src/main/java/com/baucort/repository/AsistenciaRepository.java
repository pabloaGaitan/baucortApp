package com.baucort.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baucort.entities.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long>{
	
	@Query("SELECT a FROM Estudiante e JOIN e.asistencias a WHERE e.codigo = ?1 AND a.fecha BETWEEN ?2 AND ?3 ORDER BY a.fecha")
	List<Asistencia> getAsistenciasEstudianteMes(String codigo, LocalDate fechaInicial, LocalDate fechaFinal);

}
