package coop.tecso.examen.dto;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Personas")
public class PersonaDto implements Serializable {

	private static final long serialVersionUID = -6530283173402198602L;

	@Id
	@Column(name = "ID_PERSONA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String rut;
	
	@NotNull
	@Column(name = "TIPO_PERSONA")
	private String tipo;
	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "ANIO_FUNDACION")
	private Integer anioFundacion;
	private String nombre;
	private String apellido;
	
	@Column(name = "CUENTA_CORRIENTE")
	private String cuentaCorriente;
	
	public Long getId() {
		return id;
	}
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getAnioFundacion() {
		return anioFundacion;
	}
	public void setAnioFundacion(Integer anioFundacion) {
		this.anioFundacion = anioFundacion;
	}

}
