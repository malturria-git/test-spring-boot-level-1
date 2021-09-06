package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.dto.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	User findUserByUsername(String Username);

}
