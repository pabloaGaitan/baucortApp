package com.baucort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baucort.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
	
	Estudiante findByCodigo(String codigo);

}
