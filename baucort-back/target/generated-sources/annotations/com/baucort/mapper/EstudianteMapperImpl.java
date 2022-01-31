package com.baucort.mapper;

import com.baucort.controller.dto.EstudianteDto;
import com.baucort.entities.Curso;
import com.baucort.entities.Estudiante;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-29T17:57:14-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0-262 (OpenLogic-OpenJDK)"
)
public class EstudianteMapperImpl implements EstudianteMapper {

    @Override
    public EstudianteDto estudianteToEstudianteDto(Estudiante estudiante) {
        if ( estudiante == null ) {
            return null;
        }

        EstudianteDto estudianteDto = new EstudianteDto();

        estudianteDto.setNumeroAlmuerzosDisponible( calcularNumeroAlmuerzos( estudiante ) );
        estudianteDto.setCurso( estudianteCursoCurso( estudiante ) );
        estudianteDto.setId( estudiante.getId() );
        estudianteDto.setNombresApellidosAcudiente( estudiante.getNombresApellidosAcudiente() );
        estudianteDto.setTelefonoAcudiente( estudiante.getTelefonoAcudiente() );
        estudianteDto.setCorreoAcudiente( estudiante.getCorreoAcudiente() );
        estudianteDto.setDireccion( estudiante.getDireccion() );
        estudianteDto.setNombresApellidosAcudiente2( estudiante.getNombresApellidosAcudiente2() );
        estudianteDto.setTelefonoAcudiente2( estudiante.getTelefonoAcudiente2() );
        estudianteDto.setCorreoAcudiente2( estudiante.getCorreoAcudiente2() );
        estudianteDto.setDireccion2( estudiante.getDireccion2() );
        estudianteDto.setNombresApellidos( estudiante.getNombresApellidos() );
        estudianteDto.setGrupo( estudiante.getGrupo() );
        estudianteDto.setCodigo( estudiante.getCodigo() );
        estudianteDto.setSaldoAlmuerzo( estudiante.getSaldoAlmuerzo() );
        estudianteDto.setSaldoLonchera( estudiante.getSaldoLonchera() );
        estudianteDto.setEsDeLonchera( estudiante.getEsDeLonchera() );
        estudianteDto.setEsDeAlmuerzo( estudiante.getEsDeAlmuerzo() );
        if ( estudiante.getDescuento() != null ) {
            estudianteDto.setDescuento( estudiante.getDescuento().longValue() );
        }
        estudianteDto.setDescuentoMotivo( estudiante.getDescuentoMotivo() );

        return estudianteDto;
    }

    @Override
    public Estudiante estudianteDtoToEstudiante(EstudianteDto estudianteDto, Curso curso) {
        if ( estudianteDto == null && curso == null ) {
            return null;
        }

        Estudiante estudiante = new Estudiante();

        if ( estudianteDto != null ) {
            estudiante.setId( estudianteDto.getId() );
            estudiante.setNombresApellidosAcudiente( estudianteDto.getNombresApellidosAcudiente() );
            estudiante.setTelefonoAcudiente( estudianteDto.getTelefonoAcudiente() );
            estudiante.setCorreoAcudiente( estudianteDto.getCorreoAcudiente() );
            estudiante.setDireccion( estudianteDto.getDireccion() );
            estudiante.setNombresApellidosAcudiente2( estudianteDto.getNombresApellidosAcudiente2() );
            estudiante.setTelefonoAcudiente2( estudianteDto.getTelefonoAcudiente2() );
            estudiante.setCorreoAcudiente2( estudianteDto.getCorreoAcudiente2() );
            estudiante.setDireccion2( estudianteDto.getDireccion2() );
            estudiante.setNombresApellidos( estudianteDto.getNombresApellidos() );
            estudiante.setGrupo( estudianteDto.getGrupo() );
            estudiante.setCodigo( estudianteDto.getCodigo() );
            estudiante.setSaldoAlmuerzo( estudianteDto.getSaldoAlmuerzo() );
            estudiante.setSaldoLonchera( estudianteDto.getSaldoLonchera() );
            estudiante.setEsDeLonchera( estudianteDto.getEsDeLonchera() );
            estudiante.setEsDeAlmuerzo( estudianteDto.getEsDeAlmuerzo() );
            if ( estudianteDto.getDescuento() != null ) {
                estudiante.setDescuento( estudianteDto.getDescuento().doubleValue() );
            }
            estudiante.setDescuentoMotivo( estudianteDto.getDescuentoMotivo() );
        }
        if ( curso != null ) {
            estudiante.setCurso( curso );
        }

        return estudiante;
    }

    private String estudianteCursoCurso(Estudiante estudiante) {
        if ( estudiante == null ) {
            return null;
        }
        Curso curso = estudiante.getCurso();
        if ( curso == null ) {
            return null;
        }
        String curso1 = curso.getCurso();
        if ( curso1 == null ) {
            return null;
        }
        return curso1;
    }
}
