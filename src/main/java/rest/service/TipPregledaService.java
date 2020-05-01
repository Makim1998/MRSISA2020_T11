package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.TipPregleda;
import rest.repository.TipPregledaRepository;

@Service
public class TipPregledaService {
	
	@Autowired
	private TipPregledaRepository tipPregledaRepository;
	
	public TipPregleda findOne(Integer integer) {
		return tipPregledaRepository.findById(integer).orElseGet(null);
	}

	public List<TipPregleda> findAll() {
		return tipPregledaRepository.findAll();
	}

	public TipPregleda save(TipPregleda tipPregleda) {
		return tipPregledaRepository.save(tipPregleda);
	}

	public void remove(Integer id) {
		tipPregledaRepository.deleteById(id);
	}


}
