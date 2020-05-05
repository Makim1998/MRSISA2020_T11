package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.MedicinskaSestra;

public interface MSRepository extends JpaRepository<MedicinskaSestra, Integer>{

	MedicinskaSestra findOneByEmail(String email);

}
