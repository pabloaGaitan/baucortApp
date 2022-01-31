package com.baucort.controller;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baucort.controller.dto.InfomePagoDto;
import com.baucort.controller.dto.PagoDto;
import com.baucort.entities.FechaAlmuerzoDescontado;
import com.baucort.mapper.PagoMapper;
import com.baucort.services.PagoService;

@RestController
@RequestMapping("pago")
@CrossOrigin
public class PagoController {
	
	@Autowired
	private PagoService pagoService;
	
	private PagoMapper mapper = Mappers.getMapper(PagoMapper.class);
	
	@PostMapping("/estudiante/{codigo}")
	public void registrarPago(@PathVariable("codigo") String codigo, @RequestBody PagoDto pagoDto ) {
		pagoService.registrarPago(mapper.pagoDtoToPago(pagoDto), codigo);
	}
	
	@GetMapping("/fechasCobradas")
	public List<FechaAlmuerzoDescontado> getFechasCobradas() {
		return pagoService.getFechasDescontadas();
	}
	
	@GetMapping("/estudiante/{codigo}")
	public InfomePagoDto informePagos(@PathVariable("codigo") String codigo) {
		return pagoService.getInformPago(codigo);
	}
}
