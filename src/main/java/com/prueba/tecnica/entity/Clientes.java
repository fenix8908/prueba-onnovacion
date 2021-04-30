package com.prueba.tecnica.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@SequenceGenerator(name = "secuencia_clientes", sequenceName = "AUTO_INCREMENT_IDCLIENTES",allocationSize = 1)
@Data
@AllArgsConstructor
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_clientes")
	private Long id_cliente;

	private String nombre;
	private String apellido;
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	@Column(name = "numero_documento")
	private String numeroDocumento;
	private String rut;
	@Column(name = "razon_social")
	private String razonSocial;
	@Column(name = "anio_fundacion")
	private String anioFundacion;
	@Column(name = "tipo_cliente")
	private String tipoCliente;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientes")
	@JsonIgnoreProperties("clientes")
	private List<Cuentas> cuentas;
	
	public Clientes() {
	}
	public Clientes(String nombre, String apellido, String tipoDocumento, String numeroDocumento, String rut,
			String razonSocial, String anioFundacion, String tipoCliente) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.anioFundacion = anioFundacion;
		this.tipoCliente = tipoCliente;
	}

	
	
	
	

}
