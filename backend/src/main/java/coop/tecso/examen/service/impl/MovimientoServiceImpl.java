package coop.tecso.examen.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.Movimiento;
import coop.tecso.examen.dto.MovimientoDto;
import coop.tecso.examen.enumeraciones.Monedas;
import coop.tecso.examen.repository.ICuentaCorrienteRepository;
import coop.tecso.examen.repository.IMovimientoRepository;
import coop.tecso.examen.service.MovimientoService;
import coop.tecso.examen.utils.Utils;

@RestController
@RequestMapping("/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoServiceImpl implements MovimientoService {
	
	@Autowired
	private IMovimientoRepository movimientoRepository;  
	
	@Autowired
	private ICuentaCorrienteRepository cuentasRepository;   
	
	final BigDecimal DESCUBIERTO_PESOS = new BigDecimal(-1000);
	final BigDecimal DESCUBIERTO_EUROS = new BigDecimal(-150);
	final BigDecimal DESCUBIERTO_DOLARES = new BigDecimal(-300);
	
	@Override
	public ResponseEntity<String> guardarMovimiento( Movimiento movimiento ) {

		CuentaCorriente cuenta;
		BigDecimal saldoNuevo;
		// 1 recupero la cuenta del movimiento.
		try {
			cuenta = cuentasRepository.findByCuenta( movimiento.getCuenta().getCuenta() );
		}catch (Exception e) {
			return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
		}
		
		//2 Realizo validacion de movimiento
		List<String> errores = validarMovimientos(movimiento,cuenta); 

		//2 si hay errores los informo.
		if(!errores.isEmpty())
    		return new ResponseEntity<String>( Utils.armarRetornoDeErrores(errores),HttpStatus.NOT_ACCEPTABLE); 		

    	//no hay errores de validaciones:
		try {
			
			//actualizo el saldo
		   	if( movimiento.getTipo().equals("DEBITO") ) 
	    		saldoNuevo = cuenta.getSaldo().subtract( movimiento.getImporte() );
		   	else
		   		saldoNuevo = cuenta.getSaldo().add( movimiento.getImporte() );
		   	cuenta.setSaldo(saldoNuevo);

		   	// guardo movimiento.
		   	movimiento.setCuenta(cuenta);
			movimientoRepository.save(movimiento);
		   	
		} catch (Exception e) {
    		return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
		}

		return new ResponseEntity<String>("{\"OK\": \"Movimiento registrado\"}",HttpStatus.OK);    
	}
	
	@Override
	public List<MovimientoDto> buscarMovimientoPorCuenta(String cuenta) {
		List<MovimientoDto> movimientos = new ArrayList<>();
		if(cuenta == null || cuenta.length() == 0) {
			for ( Movimiento entity : movimientoRepository.findAll() ) {
				movimientos.add( Utils.movimientoModelTODto(entity) );
			}	

			movimientos.sort( Comparator.comparing(MovimientoDto::getCuenta).reversed()
										.thenComparing(MovimientoDto::getFecha).reversed() );

		}else {
			CuentaCorriente cuentaAux = cuentasRepository.findByCuenta(cuenta);
			for (Movimiento entity : movimientoRepository.findMovimientoDtoByCuenta( cuentaAux ) ) {
				movimientos.add( Utils.movimientoModelTODto(entity) );
			}	
			
			movimientos.sort( Comparator.comparing(MovimientoDto::getFecha).reversed() );

		}
	
	    return movimientos;
	}
	

	
	/**
     * Funcion para validar los movimientos. 
     * Validaciones:
     * movimientos poseen fecha (tomar horario UTC),
	 * tipo de movimiento (débito o crédito),
     * descripción (200 caracteres) e
     * importe (numérico de 2 decimales
	 * 
     * 
     * @param MovimientoDto movimiento: instancia de la clase 
     * @return Lista de errores
     */
	private List<String> validarMovimientos(Movimiento movimiento, CuentaCorriente cuenta){
		List<String> errores = new ArrayList<>();

    	//valido NULLs
		//Valido que la cuenta exista.
    	if( cuenta == null && movimiento.getCuenta() != null && movimiento.getCuenta().getCuenta().length() != 0 ) 
    		errores.add("\"cuenta_inexistente\": \"La cuenta corriente no existe en el maestro de cuentas\"");
    	
		    	
		//cuenta:
		//Valido que no sea null.
    	if( movimiento.getCuenta() == null || movimiento.getCuenta().getCuenta().length() == 0 ) 
    		errores.add("\"cuenta_null\": \"La cuenta corriente es obligatoria\"");
    			
		//Fecha:
		//Valido que no sea null. 
    	if( movimiento.getFecha() == null ) 
    		errores.add("\"fecha_null\": \"La fecha es obligatoria\"");
    	//tipo
    	if( movimiento.getTipo() == null || movimiento.getTipo().length() == 0)
    		errores.add("\"tipo_null\": \"El tipo de movimiento es obligatorio\"");
    	//valido que no sea null
    	if( movimiento.getImporte() == null ) 
    		errores.add("\"importe_null\": \"El importe es obligatorio\"");
    	    	
    	
    	if(!errores.isEmpty())
			return errores;
    	    	
    	if( !(movimiento.getTipo().equals("CREDITO")  || movimiento.getTipo().equals("DEBITO")) )
    		errores.add("\"tipo_invalido\": \"El tipo de movimiento es invalido. Solo se acepta CREDITO o DEBITO\"");  
    	
    	//Descripcion
    	//Valido que no supere los 200 caracteres
    	if( movimiento.getDescripcion().length() > 200 )
    		errores.add("\"descripcion_larga\": \"La descripcion no puede superar los 200 caracteres\"");
    	
    	//Importe

    	else if(movimiento.getImporte().compareTo(BigDecimal.ZERO) <= 0) {
    		errores.add("\"importe_invalido\": \"El importe debe ser positivo\"");
    	}
    		
		//- Si un movimiento genera un descubierto mayor a 1000 (para cuentas en pesos), 300 (para cuentas en dólares) o 150 (para cuentas en euros)... se deberá rechazar.
    	// Todos los creditos suman al saldo y los dejo pasar. Solo valido si el debito genera un descubierto 
     	if( movimiento.getTipo().equals("DEBITO") ) {
    		BigDecimal saldoNuevo = cuenta.getSaldo().subtract( movimiento.getImporte() );
    		if( cuenta.getMoneda().equals(Monedas.PESO) && saldoNuevo.compareTo(DESCUBIERTO_PESOS) < 0 )
    			errores.add("\"saldo_descubierto\": \"El movimiento genera un descubierto de " + saldoNuevo.toString() + ". Movimiento rechado.\"");
    		
    		if( cuenta.getMoneda().equals(Monedas.DOLAR) && saldoNuevo.compareTo(DESCUBIERTO_DOLARES) < 0 )
    			errores.add("\"saldo_descubierto\": \"El movimiento genera un descubierto de " + saldoNuevo.toString() + ". Movimiento rechado.\"");
    	
    		if( cuenta.getMoneda().equals(Monedas.EURO) && saldoNuevo.compareTo(DESCUBIERTO_EUROS) < 0 )
    			errores.add("\"saldo_descubierto\": \"El movimiento genera un descubierto de " + saldoNuevo.toString() + ". Movimiento rechado.\"");
    	}
    	return errores;
	}	
}

