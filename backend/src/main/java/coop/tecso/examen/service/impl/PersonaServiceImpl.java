package coop.tecso.examen.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import coop.tecso.examen.dto.PersonaDto;
import coop.tecso.examen.model.PersonaFisica;
import coop.tecso.examen.model.PersonaJuridica;
import coop.tecso.examen.repository.IPersonaRepository;
import coop.tecso.examen.service.PersonaService;
import coop.tecso.examen.utils.Utils;

@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private IPersonaRepository personaRepository;  
	
	
	@Override
	public String buscarPorRUT(String rut) {
	
		PersonaDto personaDto = personaRepository.findPersonaByRut(rut);
		
		//si existe, instancio persona y devuelvo el toString con formato JSON.
		if( personaDto != null ) {
			if(personaDto.getTipo().equals("Fisica")) {
				PersonaFisica personaFisica = new PersonaFisica(rut, personaDto.getNombre(), personaDto.getApellido(), personaDto.getCuentaCorriente());
				return personaFisica.toString();
			}else if(personaDto.getTipo().equals("Juridica")) {
				PersonaJuridica personaJuridica = new PersonaJuridica(rut, personaDto.getRazonSocial(),personaDto.getAnioFundacion());
				return personaJuridica.toString();
			}
		}
	
		return "{\"ERROR\": \"No existe un titular con el RUT: " + rut + "\"}";
	}
	
	@Override
	public List<PersonaDto> buscarTodos() {
		
		List<PersonaDto> personas = new ArrayList<>();
		for (PersonaDto entity : personaRepository.findAll()) {
			personas.add(entity);
		}		
	    return personas;
    }

	@Override
	public ResponseEntity<String> guardarPersona(PersonaDto persona){
    	
		//1 Hago validaciones de datos provenientes del front.
		List<String> errores = validarPersonas(persona); 
		
		//2 si hay errores los informo.
    	if(!errores.isEmpty())
    		return new ResponseEntity<String>( Utils.armarRetornoDeErrores(errores),HttpStatus.NOT_ACCEPTABLE);    

    	//no hay errores de validaciones:
    	try {
			// 3 Reviso que no exista la persona.
    		PersonaDto personaDB = personaRepository.findPersonaByRut(persona.getRut());
    			
			if(personaDB != null) 
				return new ResponseEntity<String>("{\"error_rutdupl\": \"Ya existe una persona con el rut " + persona.getRut() + " ya existe\"}",HttpStatus.NOT_ACCEPTABLE);    
				
			//grabo
			try {
				personaRepository.save(persona);
			} catch (Exception e) {
        		return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
			}
		}catch (Exception e) {
			return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
		}

		return new ResponseEntity<String>("{\"OK\": \"Persona registrada\"}",HttpStatus.OK);    
	}
	
	@Override
	public ResponseEntity<String> borrarPorRUT(PersonaDto persona) {
    	
		//Controlo que la persona no este vacia. De estarlo, lo informo.
		if( persona.getRut() == null || persona.getRut().length() == 0 ) 
			return new ResponseEntity<String>("{\"rut_vacio\": \"El rut es obligatorio\"}",HttpStatus.OK);    

		
		try {
			PersonaDto personaDB = personaRepository.findPersonaByRut( persona.getRut() );

			//si personaDB es null, se esta intenta borrar una persona que no existe. lo informo.
			if(personaDB == null)
				return new ResponseEntity<String>("{\"ALERT\": \"El RUT " + persona.getRut() + " No se encuentra registrado\"}",HttpStatus.OK);
			
			try {
				personaRepository.deleteById( personaDB.getId() );
			} catch (Exception e) {
				return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST); 
			}
			
		}catch (Exception e) {
		return new ResponseEntity<String>("{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);     
		}

		return new ResponseEntity<String>("{\"OK\": \"RUT " + persona.getRut() + " Borrado\"}",HttpStatus.OK);   
	}
	
	
	public ResponseEntity<String> modificarPersona(@RequestParam PersonaDto persona) {
		
		//1 Hago validaciones de datos provenientes del front.
		List<String> errores = validarPersonas(persona); 
		
		//2 si hay errores los informo.
    	if(!errores.isEmpty())
    		return new ResponseEntity<String>( Utils.armarRetornoDeErrores(errores),HttpStatus.NOT_ACCEPTABLE);    

    	//no hay errores de validaciones:
    	try {
			// 3 Reviso que no exista la persona.
    		PersonaDto personaDB = personaRepository.findPersonaByRut(persona.getRut());
    			
			if(personaDB == null) 
				return new ResponseEntity<String>("{\"error_inexistente\": \"NO existe una persona con el rut " + persona.getRut() + "\"}",HttpStatus.NOT_ACCEPTABLE);    
				
			//grabo
			try {
				personaDB.setAnioFundacion(persona.getAnioFundacion());
				personaDB.setRazonSocial(persona.getRazonSocial());
				personaDB.setNombre(persona.getNombre());
				personaDB.setApellido(persona.getApellido());
				personaDB.setCuentaCorriente(persona.getCuentaCorriente());
				personaRepository.save(personaDB);
			} catch (Exception e) {
        		return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
			}
		}catch (Exception e) {
			return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
		}

		return new ResponseEntity<String>("{\"OK\": \"persona actualizada\"}",HttpStatus.OK);    
	}
		
	private List<String> validarPersonas(PersonaDto persona) {
    	List<String> errores = new ArrayList<>();
    	//valido NULLs
    	if(persona.getRut() == null || persona.getRut().length() == 0)
    		errores.add("\"rut_vacio\": \"El rut es obligatorio\"");
    	if(persona.getTipo() == null || persona.getTipo().length() == 0)
    		errores.add("\"tipo_vacio\": \"El tipo de persona es obligatorio\"");    	
    	
    	//Si uno de estos datos es null, no continuo y lo informo.
    	if(!errores.isEmpty())
    		return errores;
    	
    	if(persona.getTipo().equals("Fisica"))
    		errores = validarPersonaFisica(persona); 
		else if(persona.getTipo().equals("Juridica"))
			errores = validarPersonaJuridica(persona);
		else
    		errores.add("\"tipo_invalido\": \"El tipo de persona es invalido\"");
    	
    	return errores;    	
	}
	
    private List<String> validarPersonaFisica(PersonaDto persona) {
    	List<String> errores = new ArrayList<>();
    	//valido NULLs
      	if(persona.getNombre() == null || persona.getNombre().length() == 0)
    		errores.add("\"nombre_vacio\": \"El nombre es obligatorio\"");
    	if(persona.getApellido() == null || persona.getApellido().length() == 0)
    		errores.add("\"apellido_vacio\": \"El apellido es obligatorio\"");
    	if(persona.getCuentaCorriente() == null || persona.getCuentaCorriente().length() == 0)
    		errores.add("\"cuentaCorriente_vacio\": \"La cuenta corriente es obligatoria\"");
    	
    	//Si uno de estos datos es null, no continuo y lo informo.
    	if(!errores.isEmpty())
    		return errores;
    	
    	if(persona.getNombre().length() > 80)
    		errores.add("\"nombre_largo\": \"El nombre puede tener como máximo 80 caracteres\"");
    	if(persona.getApellido().length() > 255)
    		errores.add("\"apellido_largo\": \"El apellido puede tener como máximo 255 caracteres\"");

    	return errores;
    }
    
    private List<String> validarPersonaJuridica(PersonaDto persona) {
    	List<String> errores = new ArrayList<>();
    	if(persona.getRazonSocial() == null || persona.getRazonSocial().length() == 0)
    		errores.add("\"razonSocial_vacio\": \"La razón social es obligatoria\"");
    	if(persona.getAnioFundacion() == null || persona.getAnioFundacion().toString().length() == 0)
    		errores.add("\"anioFundacion_vacio\": \"El año de fundación es obligatorio\"");
    	    	
    	//Si uno de estos datos es null, no continuo y lo informo.
    	if(!errores.isEmpty())
    		return errores;
    	
    	if(persona.getRazonSocial().length() > 100)
    		errores.add("\"razonSocial_largo\": \"La razón social puede tener como máximo 100 caracteres\"");
    	if(persona.getAnioFundacion().toString().length() > 4)
    		errores.add("\"anioFundacion_largo\": \"El año de fundación puede tener como máximo 4 dígitos\"");
    	//prevalidaciones
    	try{
    		Integer auxAnioFundacion = Integer.valueOf(persona.getAnioFundacion().toString());
    	}catch(NumberFormatException e) {
    		errores.add("\"anioFundacion_invalido\": \"El año de fundación es inválido\"");
    	}
    	return errores;
    }
}