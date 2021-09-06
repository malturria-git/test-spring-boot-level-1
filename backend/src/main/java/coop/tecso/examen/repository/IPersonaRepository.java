package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.dto.PersonaDto;

public interface IPersonaRepository extends JpaRepository<PersonaDto, Long> {
	PersonaDto findPersonaByRut(String rut);

}
