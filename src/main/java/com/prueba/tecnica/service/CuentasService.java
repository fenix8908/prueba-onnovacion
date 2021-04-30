package com.prueba.tecnica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prueba.tecnica.entity.Cuentas;
import com.prueba.tecnica.repository.CuentasRepository;

@Service
public class CuentasService {

	@Autowired
	private CuentasRepository cuentasRepository;

	public List<Cuentas> obtenerCuentas() {
		return cuentasRepository.findAll();
	}

	public void crearCuenta(Cuentas cuentas) {

		this.cuentasRepository.save(cuentas);
	}

	public boolean validarExistenciaCuenta(String numeroCuenta) {
		return cuentasRepository.existsByNumeroCuenta(numeroCuenta);
	}

	public boolean validarExistenciaPorId(Long id) {
		return cuentasRepository.existsById(id);
	}

	public Optional<Cuentas> buscarCuentaPorId(Long id) {
		return cuentasRepository.findById(id);
	}

	public void eliminarCuentaPorId(Long id) {
		cuentasRepository.deleteById(id);
	}

	public void actualizarSaldo(Double saldo, Long id_cuenta) {
		cuentasRepository.editarSaldo(saldo, id_cuenta);
	}

	public Optional<Cuentas> buscarSaldoYmodedaPorId(Long id) {
			return cuentasRepository.findById(id);
	}
}
