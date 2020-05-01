package rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Pregled;
import rest.domain.Sala;
import rest.pk.SalaPK;

public interface PregledRepository extends JpaRepository<Pregled,Integer> {
	List<Pregled> findByKartonIsNull();

	List<Pregled> findByKartonIsNotNullAndSala(Sala id);

}
