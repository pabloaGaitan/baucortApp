package com.baucort.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baucort.entities.Asistencia;
import com.baucort.entities.Estudiante;
import com.baucort.repository.AsistenciaRepository;
import com.baucort.repository.EstudianteRepository;

@Service
public class AsistenciaService {
	
	@Autowired
	private AsistenciaRepository asistenciaRepository;
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	public List<String> crearAsistencia(LocalDate fecha, String[] codigos) {
		List<String> estudiantesNoEncontrados = new ArrayList<>();
		for (String codigoEstudiante : codigos) {
			Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);
			if (estudiante != null) {
				Asistencia asistencia = new Asistencia();
				asistencia.setFecha(fecha);
				asistencia = asistenciaRepository.save(asistencia);
				estudiante.addAsistencia(asistencia);
				estudianteRepository.save(estudiante);
			}else {
				estudiantesNoEncontrados.add(codigoEstudiante);
			}
		}
		
		return estudiantesNoEncontrados;
		
	}
	
	public List<Asistencia> reporteAsistencias(String codigo,LocalDate mes){
		LocalDate fechaInicial = mes.withDayOfMonth(1);
		LocalDate fechaFinal = mes.withDayOfMonth(mes.lengthOfMonth());
		return asistenciaRepository.getAsistenciasEstudianteMes(codigo, fechaInicial, fechaFinal);
	}
}
