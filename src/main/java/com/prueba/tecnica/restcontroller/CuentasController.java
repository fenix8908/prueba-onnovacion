package com.prueba.tecnica.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.dto.MensajeRespuesta;
import com.prueba.tecnica.entity.Cuentas;
import com.prueba.tecnica.excepciones.ResourceNotFoundException;
import com.prueba.tecnica.service.ClienteService;
import com.prueba.tecnica.service.CuentasService;

@RestController
@RequestMapping("/prueba/v1/cuentas")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CuentasController {

	private Cuentas cuentas;

	@Autowired
	private CuentasService cuentasService;

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public ResponseEntity<List<Cuentas>> listarCuentas() {
		List<Cuentas> listado = cuentasService.obtenerCuentas();
		return ResponseEntity.ok(listado);
	}

	@PostMapping("/crear/{cliente_id}")
	public ResponseEntity<?> guardarCliente(@RequestBody Cuentas cuentas, @PathVariable("cliente_id") Long cliente_id) {

		if (!clienteService.validarExistenciaPorId(cliente_id)) {
			return new ResponseEntity(new MensajeRespuesta("El cliente con id:'" + cliente_id + "' no existe"),
					HttpStatus.NOT_FOUND);
		}
		if (cuentasService.validarExistenciaCuenta(cuentas.getNumeroCuenta())) {
			return new ResponseEntity(new MensajeRespuesta("La cuenta ingresada ya existe"), HttpStatus.BAD_REQUEST);
		}
		this.cuentas = cuentas;
		clienteService.buscarClientePorId(cliente_id).map((cliente) -> {
			this.cuentas.setClientes(cliente);
			return this.cuentas;
		}).orElseThrow(() -> new ResourceNotFoundException("Clientes", "cliente_id", cliente_id));
		cuentasService.crearCuenta(cuentas);
		return new ResponseEntity(new MensajeRespuesta("La cuenta ha sido creada"), HttpStatus.OK);
	}
	
	

}
