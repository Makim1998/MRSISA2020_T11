package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Operacija;
import rest.repository.OperacijaRepository;
import rest.repository.PregledRepository;

@Service
public class OperacijaService {
	

	@Autowired
	private OperacijaRepository operacijaRepository;

	public Operacija save(Operacija pregled) {
		return operacijaRepository.save(pregled);
		
	}

}
