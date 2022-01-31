package com.baucort.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baucort.controller.dto.LocalDateWrapper;
import com.baucort.controller.dto.ReporteDiaDto;
import com.baucort.entities.Asistencia;
import com.baucort.entities.FechaAlmuerzoDescontado;
import com.baucort.entities.Inasistencia;
import com.baucort.repository.AsistenciaRepository;
import com.baucort.repository.FechaAlmuerzoDescontadoRepository;
import com.baucort.repository.InasistenciaRepository;

@Service
public class ReporteService {
	
	@Autowired
	private AsistenciaRepository asistenciaRepository;
	
	@Autowired
	private InasistenciaRepository inasistenciaRepository;
	
	@Autowired
	private FechaAlmuerzoDescontadoRepository fechaAlmuerzoDescontadoRepository;

	
	public List<List<ReporteDiaDto>> reporteAsistencias(String codigo,LocalDate mes){
		LocalDate fechaInicial = mes.withDayOfMonth(1);
		LocalDate fechaFinal = mes.withDayOfMonth(mes.lengthOfMonth());
		List<Asistencia> asistencias = asistenciaRepository.getAsistenciasEstudianteMes(codigo, fechaInicial, fechaFinal);
		List<Inasistencia> inasistencias = inasistenciaRepository.getInasistenciasEstudianteMes(codigo, fechaInicial, fechaFinal);
		List<FechaAlmuerzoDescontado> almuerzoDescontado = fechaAlmuerzoDescontadoRepository.findByFechaBetweenOrderByFecha(fechaInicial, fechaFinal);
		return construirInformacionCalendario(asistencias, inasistencias, almuerzoDescontado, mes);
	}

	private List<List<ReporteDiaDto>> construirInformacionCalendario(List<Asistencia> asistencias, List<Inasistencia> inasistencias,
			List<FechaAlmuerzoDescontado> almuerzoDescontado, LocalDate mes) {
		
		LocalDateWrapper fechaIterar = new LocalDateWrapper(1, mes.getMonthValue(), mes.getYear());
		List<List<ReporteDiaDto>> datosCalendario = new ArrayList<>();
		List<ReporteDiaDto> reporteDia = null;
		
		for (int i = 1; i < 31; i++) {
			
			Asistencia asistenciaDia = asistencias.stream().filter(a -> a.getFecha().equals(fechaIterar.getFecha())).findFirst().orElse(null);
			Inasistencia inasistenciaDia = inasistencias.stream().filter(a -> a.getFecha().equals(fechaIterar.getFecha())).findFirst().orElse(null);
			FechaAlmuerzoDescontado fechaAlmuerzoDesc = almuerzoDescontado.stream().filter(a -> a.getFecha().equals(fechaIterar.getFecha())).findFirst().orElse(null);
			ReporteDiaDto dto = null;
			
			//Nada probablemente festivo
			if(asistenciaDia == null && inasistenciaDia == null && fechaAlmuerzoDesc == null) {
				datosCalendario.add(new ArrayList<>());
			}
			
			//Asistencia
			if(asistenciaDia != null && inasistenciaDia == null && fechaAlmuerzoDesc!= null) {
				dto = new ReporteDiaDto();
				dto.setContent("Asistencia");
				dto.setType("success");
				reporteDia = new ArrayList<>();
				reporteDia.add(dto);
				datosCalendario.add(reporteDia);
			}
			
			//Inasistencia no reportada
			if(asistenciaDia == null && inasistenciaDia == null && fechaAlmuerzoDesc!= null) {
				dto = new ReporteDiaDto();
				dto.setContent("Inasistencia no reportada");
				dto.setType("error");
				reporteDia = new ArrayList<>();
				reporteDia.add(dto);
				datosCalendario.add(reporteDia);
			}
			
			//Inasistencia reportada
			if(asistenciaDia == null && inasistenciaDia != null && fechaAlmuerzoDesc!= null) {
				dto = new ReporteDiaDto();
				dto.setContent("Inasistencia reportada");
				dto.setType("warning");
				reporteDia = new ArrayList<>();
				reporteDia.add(dto);
				datosCalendario.add(reporteDia);
			}
			
			fechaIterar.sumarDia();
			
		}
		return datosCalendario;
		
	}
}
