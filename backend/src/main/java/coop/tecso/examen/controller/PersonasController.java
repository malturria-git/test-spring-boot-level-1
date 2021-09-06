package coop.tecso.examen.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import coop.tecso.examen.dto.PersonaDto;
import coop.tecso.examen.service.PersonaService;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "*")
public class PersonasController {

	@Autowired
	private PersonaService personaService;  
	
	@GetMapping("/buscarPorRUT")
	public String buscarPorRUT(@RequestParam String rut) {
    	return personaService.buscarPorRUT(rut);    	
    }
	
	@GetMapping("/buscarTodos")
    public List<PersonaDto> buscarTodos() {
    	return personaService.buscarTodos();
    }
    
	@RequestMapping(value = "/guardarPersona", method = RequestMethod.POST)
	public ResponseEntity<String> guardarPersona(@RequestBody PersonaDto persona){
		return personaService.guardarPersona(persona);
	}

	
	@RequestMapping(value = "/borrarPorRUT", method = RequestMethod.DELETE)
	public ResponseEntity<String> borrarPorRUT(@RequestBody PersonaDto persona) {
    	return personaService.borrarPorRUT(persona);
    }
    
	@RequestMapping(value = "/modificarPersona", method = RequestMethod.PUT)
	public ResponseEntity<String> modificarPersona(@RequestBody PersonaDto persona) {
    	return personaService.modificarPersona(persona);
    }    
}    

