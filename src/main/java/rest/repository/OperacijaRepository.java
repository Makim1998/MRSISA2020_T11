package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Operacija;
import rest.domain.Pregled;

public interface OperacijaRepository  extends JpaRepository<Operacija,Integer> {

}
