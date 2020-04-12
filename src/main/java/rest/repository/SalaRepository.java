package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Sala;
import rest.domain.TipPregleda;

public interface SalaRepository extends JpaRepository<Sala,Integer>{

}