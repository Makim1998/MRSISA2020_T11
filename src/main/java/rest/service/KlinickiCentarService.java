package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.KlinickiCentar;
import rest.repository.KlinickiCentarRepository;

@Service
public class KlinickiCentarService {
	
	@Autowired
	private KlinickiCentarRepository repository;
	
	public KlinickiCentar findOne(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<KlinickiCentar> findAll(){
		return repository.findAll();
	}
	
	public KlinickiCentar save(KlinickiCentar kc) {
		return repository.save(kc);
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}

}
