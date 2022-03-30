package com.baucort.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.baucort.entities.Estudiante;
import com.baucort.entities.FechaAlmuerzoDescontado;
import com.baucort.entities.Inasistencia;
import com.baucort.repository.EstudianteRepository;
import com.baucort.repository.FechaAlmuerzoDescontadoRepository;

@Service
public class EstudianteService {
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Autowired
	private FechaAlmuerzoDescontadoRepository fechaAlmuerzoDescontadoRepository;
	
	public Estudiante fingBycodigo(String codigo) {
		return estudianteRepository.findByCodigo(codigo);
	}
	
	public void guardarEstudiante(Estudiante estudiante) {
		estudianteRepository.save(estudiante);
	}
	
	public void descuentoAlmuerzoDia(LocalDate dia) {
		
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		Inasistencia inasistencia = null;
		FechaAlmuerzoDescontado fechaAlmuerzoDescontado = new FechaAlmuerzoDescontado();
		fechaAlmuerzoDescontado.setFecha(dia);
		for (Estudiante estudiante : estudiantes) {
			inasistencia = estudiante.getInasistencia().stream()
					.filter(i -> i.getFecha().equals(dia)).findFirst().orElse(null);
			if(inasistencia == null) {
				descontarAlmuerzo(estudiante);
			}
		}
		fechaAlmuerzoDescontadoRepository.save(fechaAlmuerzoDescontado);
		
	}

	private void descontarAlmuerzo(Estudiante estudiante) {
		Double descuento = (double) (estudiante.getDescuento() / 100);
		Long valorAlmuerzo = (long) (estudiante.getCurso().getValorAlmuerzo() * (1 - descuento));
		estudiante.setSaldoAlmuerzo(estudiante.getSaldoAlmuerzo() - valorAlmuerzo);
		estudianteRepository.save(estudiante);
	}
	
	public List<Estudiante> getAllEstudiantes(String order){
		Sort s = null;
		if (order.equals("ASC")) {
			s = Sort.by(Direction.ASC, "saldoAlmuerzo");
		} else {
			s = Sort.by(Direction.DESC, "saldoAlmuerzo");
		}
		return estudianteRepository.findAll(s);
		
	}

}
