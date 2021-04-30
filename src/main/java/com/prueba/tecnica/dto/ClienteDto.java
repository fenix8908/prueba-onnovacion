package com.prueba.tecnica.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDto {
	
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String numeroDocumento;
	private String rut;
	private String razonSocial;
	private String anioFundacion;
	private String tipoCliente;
	
	public ClienteDto() {
	}

}
