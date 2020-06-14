package rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Operacija;
import rest.domain.Pregled;

public interface OperacijaRepository  extends JpaRepository<Operacija,Integer> {

	List<Operacija> findBySalaIsNotNull();

	List<Operacija> findBySalaIsNull();

}
