package com.prueba.tecnica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@SequenceGenerator(name = "secuencia_movimientos", sequenceName = "AUTO_INCREMENT_IDMOV", allocationSize = 1)
@Data
@AllArgsConstructor
public class Movimientos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_movimientos")
	private Long id_mov;
	private String tipo;
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "cuenta_id", nullable = false, updatable = false)
	@JsonIgnoreProperties("movimientos")
	private Cuentas cuentas;
	
	public Movimientos(String tipo, Double valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public Movimientos() {
	}
	
	

}
