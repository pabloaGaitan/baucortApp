package com.baucort.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baucort.entities.Inasistencia;

public interface InasistenciaRepository extends JpaRepository<Inasistencia, Long>{
	
	@Query("SELECT a FROM Estudiante e JOIN e.inasistencia a WHERE e.codigo = ?1 AND a.fecha BETWEEN ?2 AND ?3 ORDER BY a.fecha")
	List<Inasistencia> getInasistenciasEstudianteMes(String codigo, LocalDate fechaInicial, LocalDate fechaFinal);
}
