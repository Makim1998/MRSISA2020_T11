package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Cenovnik;
import rest.domain.Karton;

public interface KartonRepository extends JpaRepository<Karton,Integer> {

}
