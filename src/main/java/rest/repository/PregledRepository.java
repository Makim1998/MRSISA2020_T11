package rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Pregled;

public interface PregledRepository extends JpaRepository<Pregled,Integer> {
	List<Pregled> findByKartonIsNull();

}
