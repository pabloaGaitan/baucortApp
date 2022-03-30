package com.baucort.mapper;

import com.baucort.controller.dto.PagoDto;
import com.baucort.entities.Pago;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-30T09:07:04-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0-262 (OpenLogic-OpenJDK)"
)
public class PagoMapperImpl implements PagoMapper {

    @Override
    public PagoDto pagoToPagoDto(Pago pago) {
        if ( pago == null ) {
            return null;
        }

        PagoDto pagoDto = new PagoDto();

        pagoDto.setFecha( pago.getFecha() );
        pagoDto.setTipo( pago.getTipo() );
        pagoDto.setNumero( pago.getNumero() );
        pagoDto.setValor( pago.getValor() );

        return pagoDto;
    }

    @Override
    public Pago pagoDtoToPago(PagoDto pagoDto) {
        if ( pagoDto == null ) {
            return null;
        }

        Pago pago = new Pago();

        pago.setFecha( pagoDto.getFecha() );
        pago.setTipo( pagoDto.getTipo() );
        pago.setNumero( pagoDto.getNumero() );
        pago.setValor( pagoDto.getValor() );

        return pago;
    }
}
