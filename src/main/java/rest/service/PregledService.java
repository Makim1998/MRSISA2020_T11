package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Pregled;
import rest.repository.PregledRepository;

@Service 

public class PregledService {

	@Autowired
	private PregledRepository pregledRepository;
	
	public List<Pregled> findSlobodne() {
		
		return pregledRepository.findByKartonIsNull();
	}

	public void remove(Integer id) {
		// TODO Auto-generated method stub
		pregledRepository.deleteById(id);
		
	}

	public Pregled findOne(Integer id) {
		// TODO Auto-generated method stub
		return pregledRepository.findById(id).orElseGet(null);
	}

	public Pregled save(Pregled pregled) {
		return pregledRepository.save(pregled);
		
	}

}
