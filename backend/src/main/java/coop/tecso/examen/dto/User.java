package coop.tecso.examen.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import coop.tecso.examen.model.AbstractPersistentObject;

@Entity

public class User extends AbstractPersistentObject{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3944557650863762830L;
	@Id
	@Column(name = "id_movimiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String token;
	private String username;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
