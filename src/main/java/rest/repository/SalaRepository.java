package rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Klinika;
import rest.domain.Sala;
import rest.domain.TipPregleda;
import rest.pk.SalaPK;

public interface SalaRepository extends JpaRepository<Sala,SalaPK>{

	List<Sala> findByKlinika(Klinika k);

}
