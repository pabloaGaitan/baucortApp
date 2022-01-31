package com.baucort.controller.dto;

import java.util.List;

public class InfomePagoDto {
	
	private List<PagoDto> pagos;
	
	private Long saldo;

	public List<PagoDto> getPagos() {
		return pagos;
	}

	public void setPagos(List<PagoDto> pagos) {
		this.pagos = pagos;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}
	
	

}
