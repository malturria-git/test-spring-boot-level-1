package coop.tecso.examen.model;

public class PersonaFisica extends Persona{
	private String nombre;
	private String apellido;
	private String cuentaCorriente;
	
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
	
	public PersonaFisica(String rut, String nombre, String apellido, String cuentaCorriente) {
		super(rut);
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuentaCorriente = cuentaCorriente;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"rut\": \""+ getRut().toString() +"\",");
		sb.append("\"nombre\": \""+ this.nombre +"\",");
		sb.append("\"apellido\": \""+ this.apellido +"\",");
		sb.append("\"tipo\": \"Fisica\",");
		sb.append("\"cuentaCorriente\": \""+ this.cuentaCorriente +"\"");
		sb.append("}");
		return sb.toString();
	}
	
	
	
}
