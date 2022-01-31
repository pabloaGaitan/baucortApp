package com.baucort.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baucort.controller.dto.ReporteDiaDto;
import com.baucort.services.AsistenciaService;
import com.baucort.services.ReporteService;

@RestController
@RequestMapping("asistencia")
@CrossOrigin
public class AsistenciaController {
	
	@Autowired
	private AsistenciaService asistenciaService;
	
	@Autowired
	private ReporteService reporteService;
	
	@PostMapping()
	public void crearAsistenciaEstudiante(@RequestParam("dia") int dia, @RequestParam("mes") int mes,
			@RequestParam("anio") int anio, @RequestParam("file") MultipartFile file) throws IOException {
		LocalDate fecha = LocalDate.of(anio, mes, dia);
		ByteArrayInputStream stream = new   ByteArrayInputStream(file.getBytes());
		
		String myString = IOUtils.toString(stream, "UTF-8");
		myString = myString.replace("[", "");
		myString = myString.replace("]", "");
		String[] codigos = myString.split("\r\n");
		System.out.println(codigos);
		asistenciaService.crearAsistencia(fecha, codigos);
	}
	
	@GetMapping("/estudiante/{codigo}/{mes}/{anio}")
	public List<List<ReporteDiaDto>> reporteAsistenciaEstudiante(@PathVariable("codigo") String codigo, 
			@PathVariable("mes") Integer mes,@PathVariable("anio") Integer anio){
		return reporteService.reporteAsistencias(codigo, LocalDate.of(anio, Month.of(mes),1));
		
	}

}
