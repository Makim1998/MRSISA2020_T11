package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.Lekar;


public interface LekarRepository extends JpaRepository<Lekar,Integer>{

	Lekar findOneByEmail(String email);

}
