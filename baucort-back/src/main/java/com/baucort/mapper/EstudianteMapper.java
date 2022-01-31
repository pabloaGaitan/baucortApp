package com.baucort.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.baucort.controller.dto.EstudianteDto;
import com.baucort.entities.Curso;
import com.baucort.entities.Estudiante;

@Mapper
public interface EstudianteMapper {
	
	@Mapping(source = "estudiante", target = "numeroAlmuerzosDisponible", qualifiedByName = "calcularAlmuerzo")
	@Mapping(source = "estudiante.curso.curso", target = "curso")
	EstudianteDto estudianteToEstudianteDto(Estudiante estudiante);
	
	@Mapping(source = "curso", target = "curso")
	@Mapping(source = "estudianteDto.id", target = "id")
	Estudiante estudianteDtoToEstudiante(EstudianteDto estudianteDto, Curso curso);
	
	@Named("calcularAlmuerzo")
	default Long calcularNumeroAlmuerzos(Estudiante estudiante) {
		return estudiante.getSaldoAlmuerzo() / estudiante.getCurso().getValorAlmuerzo();
	}

}
