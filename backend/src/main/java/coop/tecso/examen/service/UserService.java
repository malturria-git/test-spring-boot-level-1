package coop.tecso.examen.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import coop.tecso.examen.dto.User;

public interface UserService {
	ResponseEntity<String> registro( User user);
	String login(String username, String password);

}
