package coop.tecso.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.dto.CuentaCorrienteDto;
import coop.tecso.examen.dto.MovimientoDto;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.Movimiento;

public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {
	CuentaCorrienteDto findByCuenta(String cuenta);
	List<Movimiento> findMovimientoDtoByCuenta(CuentaCorriente cuenta);
}
