package com.prueba.tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.entity.Movimientos;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos, Long>{

}
