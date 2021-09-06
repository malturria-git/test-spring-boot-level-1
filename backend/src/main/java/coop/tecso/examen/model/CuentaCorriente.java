package coop.tecso.examen.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;


import coop.tecso.examen.enumeraciones.Monedas;

@Entity
@Table(name = "cuentas_corrientes")
public class CuentaCorriente extends AbstractPersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7595180240985746186L;

	@Id
	@Column(name = "id_cuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cuenta;
	
	private Monedas moneda;
	
	private BigDecimal saldo;
		  
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Monedas getMoneda() {
		return moneda;
	}

	public void setMoneda(Monedas moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public Long getId() {
		return id;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;
   
    public List<Movimiento> getMovimientos(){
    	return this.movimientos;
    }
}
