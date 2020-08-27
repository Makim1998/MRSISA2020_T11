package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Recept;
import rest.repository.ReceptRepository;

@Service
public class ReceptService {
	
	@Autowired
	private ReceptRepository repository;
	
	public Recept findOne(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Recept> findAll(){
		return repository.findAll();
	}
	
	public Recept save(Recept recept) {
		return repository.save(recept);
	}
	
	public void remove(Integer id) {
		//provjera
		repository.deleteById(id);
	}

}
