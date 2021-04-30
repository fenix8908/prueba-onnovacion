package com.prueba.tecnica.excepciones;

public class SaldoDesbordadoException extends Exception {

	private static final long serialVersionUID = 9037692200879312216L;

	public SaldoDesbordadoException(String mensaje) {
		super(mensaje);
	}

	public SaldoDesbordadoException() {
	}

}
