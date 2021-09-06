package coop.tecso.examen.dto;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class CuentaCorrienteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6700388151222151546L;

	@Id
	@Column(name = "id_cuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String cuenta;
	
	@NotNull
	private String moneda;
	
	@NotNull
	private BigDecimal saldo;

	public Long getId() {
		return id;
	}
	
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String numeroCuenta) {
		this.cuenta = numeroCuenta;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}
