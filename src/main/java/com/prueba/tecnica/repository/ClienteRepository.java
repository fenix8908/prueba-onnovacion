package com.prueba.tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.entity.Clientes;

@Repository
public interface ClienteRepository extends JpaRepository<Clientes, Long> {
	
	public boolean existsByRut(String rut);
	public boolean existsByNumeroDocumento(String documento);
	
}
