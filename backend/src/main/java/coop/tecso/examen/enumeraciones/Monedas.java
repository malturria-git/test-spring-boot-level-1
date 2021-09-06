package coop.tecso.examen.enumeraciones;

public enum Monedas {
	PESO,
    DOLAR,
    EURO,
    ;	
	
	public static Monedas getMoneda(String moneda){
		switch( moneda.toUpperCase() ) {
	      case "PESO":
	        return PESO;
	      case "DOLAR":
	        return DOLAR;
	      case "EURO":
	        return EURO;
	      default:
	    	  return null;
	    }
	}
	
	public static boolean checkMoneda(String moneda) {
		return getMoneda(moneda) == null ? false : true;
	}
}
