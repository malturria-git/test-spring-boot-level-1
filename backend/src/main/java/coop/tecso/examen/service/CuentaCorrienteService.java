package coop.tecso.examen.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import coop.tecso.examen.dto.CuentaCorrienteDto;
import coop.tecso.examen.model.CuentaCorriente;

public interface CuentaCorrienteService {
	
	public ResponseEntity<String> guardarCuentaCorriente(CuentaCorriente cuenta);
	
	public List<CuentaCorrienteDto> buscarTodasCuentas();
	
	public ResponseEntity<String> borrarCuentaCorriente(String cuenta);
}
