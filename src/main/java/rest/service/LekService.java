package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Lek;
import rest.repository.LekRepository;

@Service
public class LekService {
	
	@Autowired
	private LekRepository repository;
	
	public Lek findOne(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Lek> findAll(){
		return repository.findAll();
	}
	
	public Lek save(Lek lek) {
		return repository.save(lek);
	}
	
	public void remove(Integer id) {
		//provjera
		repository.deleteById(id);
	}

}
