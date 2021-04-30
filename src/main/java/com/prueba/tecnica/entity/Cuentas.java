package com.prueba.tecnica.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@SequenceGenerator(name = "secuencia_cuentas", sequenceName = "AUTO_INCREMENT_IDCUENTAS", allocationSize = 1)
@Data
@AllArgsConstructor
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    		name = "procedimiento_actualizar_saldo",
    		procedureName = "ACTUALIZAR_SALDO",
    		parameters = {
    				@StoredProcedureParameter(mode = ParameterMode.IN,name = "paramSaldo",type = Double.class ),
    				@StoredProcedureParameter(mode = ParameterMode.IN,name = "paramIdCuenta",type = Long.class),
    })
})
public class Cuentas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_cuentas")
	private Long id_cuenta;
	
	@Column(name = "numero_cuenta")
	private String numeroCuenta;
	private String moneda;
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, updatable = false)
	@JsonIgnoreProperties("cuentas")
	private Clientes clientes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentas")
	@JsonIgnoreProperties("cuentas")
	private List<Movimientos> movimientos;
	
	
	public Cuentas(String numeroCuenta, String moneda, Double saldo) {
		this.numeroCuenta = numeroCuenta;
		this.moneda = moneda;
		this.saldo = saldo;
	}
	
	public Cuentas() {
	}
	
}
