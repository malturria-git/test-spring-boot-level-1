package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import coop.tecso.examen.model.CuentaCorriente;

public interface ICuentaCorrienteRepository extends JpaRepository<CuentaCorriente, Long> {

	CuentaCorriente findByCuenta(String cuenta);
}	
