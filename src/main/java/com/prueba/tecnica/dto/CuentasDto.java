package com.prueba.tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentasDto {
	
	private String numeroCuenta;
	private String moneda;
	private Double saldo;

}
