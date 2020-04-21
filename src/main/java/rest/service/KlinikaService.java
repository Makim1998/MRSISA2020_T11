package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Klinika;
import rest.repository.KlinikaRepository;

@Service
public class KlinikaService {
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public Klinika findOne(Integer id) {
		return klinikaRepository.findById(id).orElse(null);
	}
	
	public List<Klinika> findAll(){
		return klinikaRepository.findAll();
	}
	
	public Klinika save(Klinika klinika) {
		return klinikaRepository.save(klinika);
	}
	
	public void remove(Integer id) {
		//provjera
		klinikaRepository.deleteById(id);
	}

}
