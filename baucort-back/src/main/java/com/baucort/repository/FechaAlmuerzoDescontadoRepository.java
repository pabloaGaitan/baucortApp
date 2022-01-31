package com.baucort.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baucort.entities.FechaAlmuerzoDescontado;

public interface FechaAlmuerzoDescontadoRepository extends JpaRepository<FechaAlmuerzoDescontado, Long> {
	
	List<FechaAlmuerzoDescontado> findByFechaBetweenOrderByFecha(LocalDate startDate, LocalDate endDate);
}
