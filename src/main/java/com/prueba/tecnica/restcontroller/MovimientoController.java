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

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.prueba.tecnica.dto.MensajeRespuesta;
import com.prueba.tecnica.entity.Cuentas;
import com.prueba.tecnica.entity.Movimientos;
import com.prueba.tecnica.excepciones.ResourceNotFoundException;
import com.prueba.tecnica.excepciones.SaldoDesbordadoException;
import com.prueba.tecnica.service.CuentasService;
import com.prueba.tecnica.service.MovimientoService;

@RestController
@RequestMapping("/prueba/v1/movimientos")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MovimientoController {

	private Movimientos movimientos;

	@Autowired
	private MovimientoService movimientoService;

	@Autowired
	private CuentasService cuentasService;

	@GetMapping("/listar")
	public ResponseEntity<List<Movimientos>> listarMovimeintos() {
		List<Movimientos> listado = movimientoService.obtenerLosMovimientos();
		return ResponseEntity.ok(listado);
	}

	@PostMapping("/crear/{cuenta_id}")
	public ResponseEntity<?> crearMovimiento(@RequestBody Movimientos movimientos,
			@PathVariable("cuenta_id") Long cuenta_id){

		if (!cuentasService.validarExistenciaPorId(cuenta_id)) {
			return new ResponseEntity(new MensajeRespuesta("La cuenta con id:'" + cuenta_id + "' no existe"),
					HttpStatus.NOT_FOUND);
		}
		this.movimientos = movimientos;
		cuentasService.buscarCuentaPorId(cuenta_id).map((cuenta) -> {
			this.movimientos.setCuentas(cuenta);
			return this.movimientos;
		}).orElseThrow(() -> new ResourceNotFoundException("Movimientos", "id_mov", cuenta_id));
		try {
			movimientoService.crearMovimiento(movimientos,cuenta_id);
		} catch (SaldoDesbordadoException e) {
			return new ResponseEntity(new MensajeRespuesta(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		cuentasService.actualizarSaldo(movimientos.getValor(), cuenta_id);
		return new ResponseEntity(new MensajeRespuesta("El movimiento ha sido creado"), HttpStatus.OK);

	}
}
