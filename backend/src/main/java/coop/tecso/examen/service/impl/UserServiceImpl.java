package coop.tecso.examen.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import coop.tecso.examen.dto.User;
import coop.tecso.examen.repository.IUserRepository;
import coop.tecso.examen.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public ResponseEntity<String> registro(User user) {
		
		if(user.getUsername().length() == 0)
			return new ResponseEntity<String>( "{\"user_null\": \" debe ingresar el usuario \"}",HttpStatus.BAD_REQUEST);    
		if(user.getPassword().length() == 0)
			return new ResponseEntity<String>( "{\"password_null\": \" debe ingresar el password \"}",HttpStatus.BAD_REQUEST);    
		
		User userdb = userRepository.findUserByUsername( user.getUsername() );
		if(userdb != null)
			return new ResponseEntity<String>( "{\"user_existente\": \" ese usuario Ya existe \"}",HttpStatus.BAD_REQUEST);    
		
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	    user.setPassword( encoder.encode( user.getPassword() ) );
	    try {
		    userRepository.save(user);
	    }catch(Exception e) {
			return new ResponseEntity<String>( "{\"error_no_controlado\": \"" + e.getMessage() + "\"}",HttpStatus.BAD_REQUEST);    
	    }
	    return new ResponseEntity<String>( "{\"OK\": \" Registrado. Puede loguear. \"}",HttpStatus.OK);   
	}

	@Override
	public String login(String username, String password) {
		
		if(username.length() == 0)
			return "{\"user_null\": \" debe ingresar el usuario\"}";
		if(password.length() == 0)
			return "{\"password_null\": \" debe ingresar el password\"}";		
		
		User userdb = userRepository.findUserByUsername( username );
		if(userdb == null)
			return "{\"user_inexistente\": \" ese usuario NO existe \"}"; 
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	    String passEncoded =  encoder.encode( password ) ;

	    

	    if( !encoder.matches(password, userdb.getPassword()) )
	    	return "{\"pass_erronea\": \" Contraseña erronea \"}";
		
		String token = getJWTToken(username);
	
    	return "{\"token\": \""+ token +"\"}";
	}
	
	private String getJWTToken(String username) {
		String secretKey = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("coop.tecso.examen")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS256,
						secretKey.getBytes()).compact();

		
		return "Bearer " + token;
	}
	
	

}