package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Recept;

public interface ReceptRepository extends JpaRepository<Recept, Integer>{

}
