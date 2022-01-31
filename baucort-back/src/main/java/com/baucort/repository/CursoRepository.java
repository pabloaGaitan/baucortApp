package com.baucort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baucort.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	Curso findByCurso(String curso);

}
