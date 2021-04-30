package com.prueba.tecnica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.tecnica.entity.Cuentas;
import com.prueba.tecnica.entity.Movimientos;
import com.prueba.tecnica.excepciones.SaldoDesbordadoException;
import com.prueba.tecnica.repository.MovimientoRepository;

@Service
public class MovimientoService {

	@Autowired
	private MovimientoRepository movimientoRepository;

	@Autowired
	private CuentasService cuentasService;

	public List<Movimientos> obtenerLosMovimientos() {
		return movimientoRepository.findAll();
	}

	public void crearMovimiento(Movimientos movimiento, Long cuenta_id) throws SaldoDesbordadoException {
		Cuentas cuentaEncontrada = cuentasService.buscarSaldoYmodedaPorId(cuenta_id).get();
		if ((cuentaEncontrada.getSaldo() + movimiento.getValor()) > 1000000
				&& cuentaEncontrada.getMoneda().equalsIgnoreCase("peso")) {
			throw new SaldoDesbordadoException("Este movimiento supera el saldo permitido de: $1,000,000");
		} else if ((cuentaEncontrada.getSaldo() + movimiento.getValor()) > 300
				&& cuentaEncontrada.getMoneda().equalsIgnoreCase("dolar")) {
			throw new SaldoDesbordadoException("Este movimiento supera el saldo permitido de: 300USD");
		} else if ((cuentaEncontrada.getSaldo() + movimiento.getValor()) > 150
				&& cuentaEncontrada.getMoneda().equalsIgnoreCase("euro")) {
			throw new SaldoDesbordadoException("Este movimiento supera el saldo permitido de: €150");
		}

		movimientoRepository.save(movimiento);
	}

}
