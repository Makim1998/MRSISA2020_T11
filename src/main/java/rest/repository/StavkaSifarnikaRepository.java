package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.StavkaSifarnika;

public interface StavkaSifarnikaRepository extends JpaRepository<StavkaSifarnika, Integer>{
	
	StavkaSifarnika findOneBySifra(String sifra);

}
