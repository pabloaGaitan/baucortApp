package com.baucort.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baucort.entities.Estudiante;
import com.baucort.entities.Inasistencia;
import com.baucort.repository.EstudianteRepository;
import com.baucort.repository.InasistenciaRepository;

@Service
public class InasistenciaService {
	
	@Autowired
	private InasistenciaRepository inasistenciaRepository;
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	public void crearInasistencia(Inasistencia inasistencia, String codigo) {
		inasistencia = inasistenciaRepository.save(inasistencia);
		Estudiante estudiante = estudianteRepository.findByCodigo(codigo);
		estudiante.getInasistencia().add(inasistencia);
		estudianteRepository.save(estudiante);
	}
}
