package coop.tecso.examen.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "movimientos")
public class Movimiento extends AbstractPersistentObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1765827318010515462L;

	@Id
	@Column(name = "id_movimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDate fecha;
	
	@NotNull
	private String descripcion;
	
	@NotNull 
	private BigDecimal importe;
		
	@NotNull
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public CuentaCorriente getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaCorriente cuenta) {
		this.cuenta = cuenta;
	}
	
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name="id_cuenta" )
    private CuentaCorriente cuenta;

}
