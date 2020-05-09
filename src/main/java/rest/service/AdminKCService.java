package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.AdministratorKlinickogCentra;
import rest.repository.AdminKCRepository;

@Service
public class AdminKCService {
	
	@Autowired
	private AdminKCRepository repository;
	
	public AdministratorKlinickogCentra save(AdministratorKlinickogCentra admkc) {
		return repository.save(admkc);
	}

	public AdministratorKlinickogCentra findOne(Integer id) {
		return repository.findById(id).orElseGet(null);
	}
	
	public List<AdministratorKlinickogCentra> findAll(){
		return repository.findAll();
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}

}
