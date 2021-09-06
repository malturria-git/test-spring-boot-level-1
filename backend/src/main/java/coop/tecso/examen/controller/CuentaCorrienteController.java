package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import coop.tecso.examen.dto.CuentaCorrienteDto;
import coop.tecso.examen.service.CuentaCorrienteService;
import coop.tecso.examen.utils.Utils;

@RestController
@RequestMapping("/cuentascorrientes")
@CrossOrigin(origins = "*")
public class CuentaCorrienteController {
	
	@Autowired
	private CuentaCorrienteService cuentaCorrienteService;  
	
	@RequestMapping(value = "/guardarCuentaCorriente", method = RequestMethod.POST)
	public ResponseEntity<String> guardarCuentaCorriente(@RequestBody CuentaCorrienteDto cuenta) throws InvalidFormatException{
		return cuentaCorrienteService.guardarCuentaCorriente( Utils.cuentaDtoTOModel(cuenta) );
	}
	
	@GetMapping("/buscarTodasCuentas")
    public List<CuentaCorrienteDto> buscarTodasCuentas() {
		return cuentaCorrienteService.buscarTodasCuentas();
		
    }
	
	@RequestMapping(value = "/borrarCuentaCorriente", method = RequestMethod.DELETE)
	public ResponseEntity<String> borrarCuentaCorriente(@RequestBody CuentaCorrienteDto cuenta) throws InvalidFormatException{
		return cuentaCorrienteService.borrarCuentaCorriente( cuenta.getCuenta() );
	}
}