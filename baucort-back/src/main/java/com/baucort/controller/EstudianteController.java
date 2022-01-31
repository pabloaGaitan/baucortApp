package com.baucort.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baucort.controller.dto.EstudianteDto;
import com.baucort.controller.dto.FechaDto;
import com.baucort.entities.Curso;
import com.baucort.entities.Estudiante;
import com.baucort.mapper.EstudianteMapper;
import com.baucort.services.CursoService;
import com.baucort.services.EstudianteService;

@RestController
@RequestMapping("estudiante")
@CrossOrigin
public class EstudianteController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private CursoService cursoService;
	
	private EstudianteMapper estudianteMapper = Mappers.getMapper(EstudianteMapper.class);
	
	@GetMapping("/{codigo}")
	public EstudianteDto findByCodigo(@PathVariable("codigo") String codigo) {
		return estudianteMapper.estudianteToEstudianteDto(estudianteService.fingBycodigo(codigo));
	}
	
	@PutMapping
	public void editarEstudiante(@RequestBody EstudianteDto dto) {
		Curso curso = cursoService.findByCurso(dto.getCurso());
		Estudiante estudiante = estudianteMapper.estudianteDtoToEstudiante(dto, curso);
		estudianteService.guardarEstudiante(estudiante);
	}
	
	@PostMapping
	public void descontarAlmuerzoDia(@RequestBody FechaDto fecha) {
		estudianteService.descuentoAlmuerzoDia(fecha.getFecha());
	}

}
