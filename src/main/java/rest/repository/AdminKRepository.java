package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.domain.AdministratorKlinike;
import rest.domain.Klinika;

public interface AdminKRepository extends JpaRepository<AdministratorKlinike,Integer> {
	AdministratorKlinike findOneByEmail(String email);

	AdministratorKlinike findOneByKlinika(Klinika klinika);
}
