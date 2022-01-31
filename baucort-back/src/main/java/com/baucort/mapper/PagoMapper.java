package com.baucort.mapper;

import org.mapstruct.Mapper;

import com.baucort.controller.dto.PagoDto;
import com.baucort.entities.Pago;

@Mapper
public interface PagoMapper {
	
	
	PagoDto pagoToPagoDto(Pago pago);
	
	
	Pago pagoDtoToPago(PagoDto pagoDto);
	
	

}
