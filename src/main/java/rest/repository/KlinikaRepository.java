package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Klinika;

public interface KlinikaRepository extends JpaRepository<Klinika, Integer>{
}
