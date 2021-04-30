package com.prueba.tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.tecnica.entity.Cuentas;

@Repository
public interface CuentasRepository extends JpaRepository<Cuentas, Long> {

	public boolean existsByNumeroCuenta(String numeroCuenta);
	
	@Procedure(name="procedimiento_actualizar_saldo")
	public void editarSaldo(@Param("paramSaldo") Double paramSaldo, @Param("paramIdCuenta") Long paramIdCuenta);
	

}
