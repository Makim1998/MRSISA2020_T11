package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Lekar;
import rest.domain.Pregled;
import rest.domain.Sala;
import rest.domain.TipPregleda;
import rest.pk.SalaPK;
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

	public List<Pregled> findZauzete(Sala id) {
		// TODO Auto-generated method stub
		return pregledRepository.findByKartonIsNotNullAndSala(id);
	}

	public List<Pregled> findZauzete(TipPregleda tip) {
		// TODO Auto-generated method stub
		return pregledRepository.findByKartonIsNotNullAndTip(tip);
	}

	public List<Pregled> findZauzete(Lekar lekar) {
		// TODO Auto-generated method stub
		return pregledRepository.findByKartonIsNotNullAndLekar(lekar);
	}

}
