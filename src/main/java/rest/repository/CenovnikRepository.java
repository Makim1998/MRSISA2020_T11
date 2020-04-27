package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Cenovnik;
import rest.domain.Klinika;
import rest.domain.User;

public interface CenovnikRepository extends JpaRepository<Cenovnik,Integer> {
	Cenovnik findOneByKlinika(Klinika klinika);

}
