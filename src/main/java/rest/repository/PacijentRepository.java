package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent,Integer> {
	Pacijent findOneByEmail(String email);
	Pacijent findOneByBrojOsiguranika(String brO);
}
