package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Klinika;
import rest.domain.User;
import rest.repository.KlinikaRepository;

@Service
public class KlinikaService {
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public Klinika findOne(Integer id) {
		return klinikaRepository.findById(id).orElse(null);
	}
	public Klinika findByNaziv(String naziv) {
		return klinikaRepository.findOneByNaziv(naziv);
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
