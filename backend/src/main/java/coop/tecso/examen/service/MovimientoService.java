package coop.tecso.examen.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import coop.tecso.examen.dto.MovimientoDto;
import coop.tecso.examen.model.Movimiento;

public interface MovimientoService {
	
	public ResponseEntity<String> guardarMovimiento(Movimiento cuenta);
	public List<MovimientoDto> buscarMovimientoPorCuenta(String cuenta);

}
