package coop.tecso.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.User;
import coop.tecso.examen.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
    	return userService.login( username, pwd );
		
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public ResponseEntity<String> registro(@RequestBody User user) {
    	return userService.registro( user );

		
	}
	
}
