package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Klinika;
import rest.domain.User;

public interface KlinikaRepository extends JpaRepository<Klinika, Integer>{

	Klinika findOneByNaziv(String naziv);

}
