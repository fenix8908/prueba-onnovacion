package com.prueba.tecnica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.entity.Clientes;
import com.prueba.tecnica.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Clientes> listarClientes() {
		return clienteRepository.findAll();
	}

	public void crearCliente(Clientes clientes) {

		this.clienteRepository.save(clientes);
	}

	public boolean validarExistenciaRut(String rut) {
		return clienteRepository.existsByRut(rut);
	}

	public boolean validarExistenciaDocumento(String documento) {
		return clienteRepository.existsByNumeroDocumento(documento);
	}

	public boolean validarExistenciaPorId(Long id) {
		return clienteRepository.existsById(id);
	}

	public Optional<Clientes> buscarClientePorId(Long id) {
		return clienteRepository.findById(id);
	}

	public void eliminarClientePorId(Long id) {
		clienteRepository.deleteById(id);
	}
}
