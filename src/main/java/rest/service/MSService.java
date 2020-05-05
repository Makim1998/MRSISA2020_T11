package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.MedicinskaSestra;
import rest.repository.MSRepository;

@Service
public class MSService {
	
	@Autowired
	private MSRepository repository;
	
	public MedicinskaSestra findOne(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<MedicinskaSestra> findAll(){
		return repository.findAll();
	}
	
	public MedicinskaSestra save(MedicinskaSestra ms) {
		return repository.save(ms);
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}
	
}
