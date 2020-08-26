package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Dijagnoza;
import rest.repository.DijagnozaRepository;

@Service
public class DijagnozaService {
	
	@Autowired
	private DijagnozaRepository repository;
	
	public Dijagnoza findOne(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Dijagnoza> findAll(){
		return repository.findAll();
	}
	
	public Dijagnoza save(Dijagnoza d) {
		return repository.save(d);
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}

}
