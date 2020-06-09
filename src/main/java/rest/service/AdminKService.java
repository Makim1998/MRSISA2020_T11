package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.AdministratorKlinike;
import rest.domain.Klinika;
import rest.repository.AdminKRepository;


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
	
	public List<AdministratorKlinike> findAll(){
		return adminKRepository.findAll();
	}
	
	public void remove(Integer id) {
		adminKRepository.deleteById(id);
	}

	public AdministratorKlinike findByKlinika(Klinika klinika) {
		// TODO Auto-generated method stub
		return adminKRepository.findOneByKlinika(klinika);
	}

}
