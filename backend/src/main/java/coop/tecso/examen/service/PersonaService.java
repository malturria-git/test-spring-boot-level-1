package coop.tecso.examen.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import coop.tecso.examen.dto.PersonaDto;

public interface PersonaService {
	String buscarPorRUT( String rut);
	ResponseEntity<String> guardarPersona(PersonaDto persona);    	
	List<PersonaDto> buscarTodos();
	ResponseEntity<String> borrarPorRUT(PersonaDto persona);
	ResponseEntity<String> modificarPersona(PersonaDto persona);
}
