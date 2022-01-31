package com.baucort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baucort.entities.Curso;
import com.baucort.services.CursoService;

@RestController
@RequestMapping("curso")
@CrossOrigin
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public List<Curso> listaCursos(){
		return cursoService.getCursos();
	}
	
	@PutMapping
	public void editarCurso(@RequestBody Curso curso) {
		cursoService.editarCurso(curso);
	}

}
