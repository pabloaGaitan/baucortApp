package com.baucort.services;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baucort.controller.dto.InfomePagoDto;
import com.baucort.controller.dto.PagoDto;
import com.baucort.entities.Estudiante;
import com.baucort.entities.FechaAlmuerzoDescontado;
import com.baucort.entities.Pago;
import com.baucort.mapper.PagoMapper;
import com.baucort.repository.EstudianteRepository;
import com.baucort.repository.FechaAlmuerzoDescontadoRepository;
import com.baucort.repository.PagoRepository;

@Service
public class PagoService {
	
	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired 
	private EstudianteRepository estudianteRepository;
	
	private PagoMapper mapper = Mappers.getMapper(PagoMapper.class);
	
	@Autowired
	private FechaAlmuerzoDescontadoRepository fechaAlmuerzoDescontadoRepository;
	
	public void registrarPago(Pago pago, String codigoEstudiante) {
		Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);
		estudiante.setSaldoAlmuerzo(estudiante.getSaldoAlmuerzo() + pago.getValor());
		estudianteRepository.save(estudiante);
		pago.setEstudiante(estudiante);
		pagoRepository.save(pago);
	}
	
	public List<FechaAlmuerzoDescontado> getFechasDescontadas(){
		return fechaAlmuerzoDescontadoRepository.findAll();
	}
	
	public InfomePagoDto getInformPago(String codigoEstudiante) {
		Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);
		List<PagoDto> pagosDto = estudiante.getPagos().stream().map(pago -> mapper.pagoToPagoDto(pago)).collect(Collectors.toList());
		InfomePagoDto dto = new InfomePagoDto();
		dto.setPagos(pagosDto);
		dto.setSaldo(estudiante.getSaldoAlmuerzo());
		return dto;
	}

}
