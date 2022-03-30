package com.baucort.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.baucort.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	
	Estudiante findByCodigo(String codigo);
	
	List<Estudiante> findAll(Sort sort);
}
