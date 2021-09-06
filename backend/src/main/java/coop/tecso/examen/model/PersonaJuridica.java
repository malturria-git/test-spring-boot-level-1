package coop.tecso.examen.model;

public class PersonaJuridica extends Persona{
	private String razonSocial;
	private Integer anioFundacion;
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Integer getAnioFundacion() {
		return anioFundacion;
	}
	public void setAnioFundacion(Integer anioFundacion) {
		this.anioFundacion = anioFundacion;
	}
	
	public PersonaJuridica(String rut, String razonSocial, Integer anioFundacion) {
		super(rut);
		this.razonSocial = razonSocial;
		this.anioFundacion = anioFundacion;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"rut\": \""+ getRut().toString() +"\",");
		sb.append("\"razonSocial\": \""+ this.razonSocial +"\",");
		sb.append("\"tipo\": \"Juridica\",");
		sb.append("\"anioFundacion\": \""+ this.anioFundacion.toString() +"\"");
		sb.append("}");
		return sb.toString();
	}

	
}
