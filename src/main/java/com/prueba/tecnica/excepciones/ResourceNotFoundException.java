package com.prueba.tecnica.excepciones;


public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String tabla;
	private String columna;
	private Object valor;

	public ResourceNotFoundException(String tabla, String columna, Object valor) {
		super(String.format("%s inexistente con %s : '%s'", tabla, columna, valor));
		this.tabla = tabla;
		this.columna = columna;
		this.valor = valor;
	}

	public String getTabla() {
		return tabla;
	}

	public String getColumna() {
		return columna;
	}

	public Object getValor() {
		return valor;
	}
}
