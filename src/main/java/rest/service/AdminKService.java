package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.AdministratorKlinike;
import rest.domain.Pacijent;
import rest.repository.AdminKRepository;
import rest.repository.PacijentRepository;


@Service 

public class AdminKService {
	@Autowired
	private AdminKRepository adminKRepository;

	public AdministratorKlinike findByEmail(String email) {
		// TODO Auto-generated method stub
		return adminKRepository.findOneByEmail(email);
	}

	public AdministratorKlinike save(AdministratorKlinike admk) {
		// TODO Auto-generated method stub
		return adminKRepository.save(admk);
	}

	public AdministratorKlinike findOne(Integer id) {
		// TODO Auto-generated method stub
		return adminKRepository.findById(id).orElseGet(null);
	}


}
