package coop.tecso.examen.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.Movimiento;
import coop.tecso.examen.dto.CuentaCorrienteDto;
import coop.tecso.examen.enumeraciones.Monedas;
import coop.tecso.examen.repository.ICuentaCorrienteRepository;
import coop.tecso.examen.repository.IMovimientoRepository;
import coop.tecso.examen.service.CuentaCorrienteService;
import coop.tecso.examen.utils.Utils;

@Service
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService {
	
	@Autowired
	private ICuentaCorrienteRepository cuentasRepository;
	@Autowired
	private IMovimientoRepository movimientoRepository;
	
	@Override
	public ResponseEntity<String> guardarCuentaCorriente(CuentaCorriente cuenta){
		

		//1 Hago validaciones de datos provenientes del front.
		List<String> errores = validarCuentasCorrientes(cuenta); 
		
		//2 si hay errores los informo.
    	if(!errores.isEmpty())
    		return new ResponseEntity<String>( Utils.armarRetornoDeErrores(errores),HttpStatus.NOT_ACCEPTABLE);    

    	//no hay errores de validaciones:
    	try {
			// 3 Reviso que no exista la cuenta.
			CuentaCorriente id = cuentasRepository.findByCuenta( cuenta.getCuenta() );
    			
			if(id != null) 
				return new ResponseEntity<String>("{\"cuenta_existe\": \"La cuenta " + cuenta.getCuenta() + " ya existe\"}",HttpStatus.NOT_ACCEPTABLE);    
				
			//grabo
			try {
    			cuentasRepository.save(cuenta);
			} catch (Exception e) {
        		return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
			}
		}catch (Exception e) {
			return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
		}

		return new ResponseEntity<String>("{\"OK\": \"Cuenta registrada\"}",HttpStatus.OK);    
	}
	
	@Override
    public List<CuentaCorrienteDto> buscarTodasCuentas() {
		
		List<CuentaCorrienteDto> cuentas = new ArrayList<>();
		for (CuentaCorriente entity : cuentasRepository.findAll() ) {
			cuentas.add( Utils.cuentaModelTODto(entity) );
		}		
	    return cuentas;
    }
	
	
	@Override
	public ResponseEntity<String> borrarCuentaCorriente(String cuenta){
    	
		//Controlo que la cuenta no este vacia. De estarlo, lo informo.
		if( cuenta == null || cuenta.length() == 0 ) 
			return new ResponseEntity<String>("{\"cuenta_null\": \"El número de la cuenta corriente es obligatorio\"}",HttpStatus.BAD_REQUEST);    

		
		try {
			CuentaCorriente id = cuentasRepository.findByCuenta( cuenta );

			//si id es null, se esta intenta borrar una cuenta que no existe. lo informo.
			if(id == null)
				return new ResponseEntity<String>("{\"cuenta_inexistente\": \"La cuenta " + cuenta + " no existe\"}",HttpStatus.BAD_REQUEST);
			
			List<Movimiento> asd = id.getMovimientos();
			if( !asd.isEmpty() )
				return new ResponseEntity<String>("{\"movimientos_existente\": \"La cuenta " + cuenta + " tiene movimientos asociados.\"}",HttpStatus.BAD_REQUEST);
			
			try {
				cuentasRepository.deleteById( id.getId() );
			} catch (Exception e) {
				return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST); 
			}
			
		}catch (Exception e) {
		return new ResponseEntity<String>("{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);     
		}

		return new ResponseEntity<String>("{\"OK\": \"Cuenta " + cuenta + " Borrada\"}",HttpStatus.OK);    
	}
	
	/**
     * Funcion para validar la cuenta corriente. 
     * Validaciones:
     * cuentas poseen un número de cuenta (valor requerido y único) (Aunque dice numero de cuenta, el tipo es String, de esa manera una cuenta puede ser NNNNN/NN-N, tener mas de 20 caracteres, ser una alias de CBU, etc.)
	 * una moneda (peso, dolar, euro)
	 * Un saldo (valor numérico de 2 decimales) (Comentario: debe ser BigDecimal.)
	 * 
     * 
     * @param CuentaCorrienteDto cuenta: instancia de la clase 
     * @return Lista de errores
     */
	private List<String> validarCuentasCorrientes(CuentaCorriente cuenta){
		List<String> errores = new ArrayList<>();
		//Valido NULLs
		//Valido que no sea null. Si es null, corto la validación y devuelvo el error.
    	if( cuenta.getCuenta() == null || cuenta.getCuenta().length() == 0 ) 
    		errores.add("\"cuenta_null\": \"El número de la cuenta corriente es obligatorio\"");
    	
    	if( cuenta.getMoneda() == null )
    		errores.add("\"moneda_null\": \"La moneda es obligatoria\"");    	

    	if( cuenta.getSaldo() == null )
    		errores.add("\"saldo_null\": \"El saldo es obligatorio\"");

    	if(!errores.isEmpty())
    		return errores;

    	//Valido el largo
    	if( cuenta.getCuenta().length() > 50 )
    		errores.add("\"cuenta_larga\": \"El número de la cuenta corriente es demasiado largo\"");    
    	
    	//Valido que sea una moneda del enumerado.
    	if( cuenta.getMoneda() != null && !Monedas.checkMoneda(cuenta.getMoneda().toString() ) )
    		errores.add("\"moneda_invalida\": \"La moneda " + cuenta.getMoneda() + " no es valida\"");
    	
    	return errores;
	}
}

