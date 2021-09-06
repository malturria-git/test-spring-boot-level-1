package coop.tecso.examen.utils;

import java.util.List;

import coop.tecso.examen.dto.CuentaCorrienteDto;
import coop.tecso.examen.dto.MovimientoDto;
import coop.tecso.examen.enumeraciones.Monedas;
import coop.tecso.examen.model.CuentaCorriente;
import coop.tecso.examen.model.Movimiento;

public class Utils {

	/**
     * Funcion para armar una lista con errores para retornar en formato JSON
     * 
     * @param List<String> errores: lista de errores
     * @return Un string "JSON"
     */
	public static String armarRetornoDeErrores(List<String> errores){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(errores.get(0));
		
		for (int i = 1; i < errores.size(); i++) {
			sb.append(",");
			sb.append(errores.get(i));
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
     * Funcion para convertir cuenta dto a cuenta model. 
     *  
     * @param CuentaCorrienteDto cuentaDto
     * @return CuentaCorriente model
     */
	public static CuentaCorriente cuentaDtoTOModel(CuentaCorrienteDto cuentaDto){
		CuentaCorriente cuentaModel = new CuentaCorriente();
		cuentaModel.setMoneda( Monedas.getMoneda(cuentaDto.getMoneda()) );
		cuentaModel.setCuenta(cuentaDto.getCuenta());
		cuentaModel.setSaldo(cuentaDto.getSaldo());
		return cuentaModel;
	}
	/**
     * Funcion para convertir cuenta model a cuenta dto. 
     *  
     * @param CuentaCorriente model
     * @return CuentaCorrienteDto cuentaDto
     */
	public static CuentaCorrienteDto cuentaModelTODto(CuentaCorriente cuentaModel){
		CuentaCorrienteDto cuentaDto = new CuentaCorrienteDto();
		cuentaDto.setMoneda( cuentaModel.getMoneda().toString() );
		cuentaDto.setCuenta( cuentaModel.getCuenta() );
		cuentaDto.setSaldo( cuentaModel.getSaldo() );
		return cuentaDto;
	}
	
	/**
     * Funcion para convertir movimiento dto a Movimiento model. 
     *  
     * @param MovimientoDto movimientoDto
     * @return movimiento model
     */
	
	public static Movimiento movimientoDtoTOModel(MovimientoDto movimientoDto){
		Movimiento movimiento = new Movimiento();
		movimiento.setFecha( movimientoDto.getFecha() );
		CuentaCorriente cuenta = new CuentaCorriente();
		cuenta.setCuenta(movimientoDto.getCuenta());
		movimiento.setCuenta( cuenta );
		movimiento.setTipo( movimientoDto.getTipo() );
		movimiento.setDescripcion( movimientoDto.getDescripcion() );
		movimiento.setImporte( movimientoDto.getImporte() );
		return movimiento;
		
	}
	/**
     * Funcion para convertir Movimiento model a Movimiento dto. 
     *  
     * @param movimiento model
     * @return MovimientoDto movimientoDto
     */
	public static MovimientoDto movimientoModelTODto(Movimiento movimiento){
		MovimientoDto movimientoDto = new MovimientoDto();
		movimientoDto.setFecha( movimiento.getFecha() );
		movimientoDto.setCuenta( movimiento.getCuenta().getCuenta() );
		movimientoDto.setTipo( movimiento.getTipo() );
		movimientoDto.setDescripcion( movimiento.getDescripcion() );
		movimientoDto.setImporte( movimiento.getImporte() );
		return movimientoDto;
	}
}
