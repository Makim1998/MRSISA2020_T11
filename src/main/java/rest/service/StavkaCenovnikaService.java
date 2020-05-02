package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.StavkaCenovnika;
import rest.repository.StavkaCenovnikaRepository;

@Service 

public class StavkaCenovnikaService {
	@Autowired
	private StavkaCenovnikaRepository stavkaCenovnikaRepository;
	
	public StavkaCenovnika save(StavkaCenovnika stavka) {
		return stavkaCenovnikaRepository.save(stavka);
		
	}
	public void remove(Integer id) {
		stavkaCenovnikaRepository.deleteById(id);
		
	}
	public StavkaCenovnika findOne(Integer id) {
		return stavkaCenovnikaRepository.findById(id).orElseGet(null);
	}

}
