package rest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Pacijent;
import rest.repository.PacijentRepository;

@Service

public class PacijentService {
	@Autowired
	private PacijentRepository pacijentRepository;
	
	public Collection<Pacijent> findAll() {
		return pacijentRepository.findAll();
	}

	
	public Pacijent findOne(Integer id) {
		return  pacijentRepository.findById(id).orElseGet(null);
	}
	
	public Pacijent findByEmail(String email) {
		return pacijentRepository.findOneByEmail(email);
	}

	public Pacijent save(Pacijent pacijent) {
		return pacijentRepository.save(pacijent);
	}

	public void remove(Integer id) {
		pacijentRepository.deleteById(id);
	}
}
