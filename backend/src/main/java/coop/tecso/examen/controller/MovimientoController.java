package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import coop.tecso.examen.dto.MovimientoDto;
import coop.tecso.examen.service.MovimientoService;
import coop.tecso.examen.utils.Utils;

@RestController
@RequestMapping("/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;  

	@RequestMapping(value = "/guardarMovimiento", method = RequestMethod.POST)
	public ResponseEntity<String> guardarMovimiento(@RequestBody MovimientoDto movimiento) {
    	return movimientoService.guardarMovimiento( Utils.movimientoDtoTOModel(movimiento) );
    }

	@GetMapping("/buscarMovimientoPorCuenta")
	public List<MovimientoDto> buscarMovimientoPorCuenta(@RequestParam(required = false) String cuenta) {
    	return movimientoService.buscarMovimientoPorCuenta(cuenta);
    }	
}