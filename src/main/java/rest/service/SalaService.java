package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Sala;
import rest.pk.SalaPK;
import rest.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
	public Sala findOne(SalaPK id) {
		return salaRepository.findById(id).orElseGet(null);
	}

	public List<Sala> findAll() {
		return salaRepository.findAll();
	}

	public Sala save(Sala sala) {
		return salaRepository.save(sala);
	}

	public void remove(SalaPK id) {
		salaRepository.deleteById(id);
	}

}
