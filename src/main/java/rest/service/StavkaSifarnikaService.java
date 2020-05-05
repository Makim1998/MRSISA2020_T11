package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.StavkaSifarnika;
import rest.repository.StavkaSifarnikaRepository;

@Service
public class StavkaSifarnikaService {
	
	@Autowired
	private StavkaSifarnikaRepository repository;
	
	public StavkaSifarnika findOne(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<StavkaSifarnika> findAll(){
		return repository.findAll();
	}
	
	public StavkaSifarnika save(StavkaSifarnika stavka) {
		return repository.save(stavka);
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}

}
