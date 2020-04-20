package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findOneByEmail(String email);
}
