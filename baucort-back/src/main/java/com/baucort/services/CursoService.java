package com.baucort.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baucort.entities.Curso;
import com.baucort.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public Curso findByCurso(String curso) {
		return cursoRepository.findByCurso(curso);
	}
	
	public void editarCurso(Curso curso) {
		cursoRepository.save(curso);
	}
	
	public List<Curso> getCursos(){
		return cursoRepository.findAll();
	}
}
