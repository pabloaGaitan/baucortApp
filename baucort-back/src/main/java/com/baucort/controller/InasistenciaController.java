package com.baucort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baucort.entities.Inasistencia;
import com.baucort.services.InasistenciaService;

@RestController
@RequestMapping("inasistencia")
@CrossOrigin
public class InasistenciaController {
	
	@Autowired
	private InasistenciaService inasistenciaService;
	
	@PostMapping("/estudiante/{codigo}")
	public void crearInasistenciaEstudiante(@PathVariable("codigo") String codigo, @RequestBody Inasistencia inasistencia ) {
		inasistenciaService.crearInasistencia(inasistencia, codigo);
	}

}
