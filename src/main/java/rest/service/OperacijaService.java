package rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Operacija;
import rest.domain.Pregled;
import rest.repository.OperacijaRepository;
import rest.repository.PregledRepository;

@Service
public class OperacijaService {
	

	@Autowired
	private OperacijaRepository operacijaRepository;

	public Operacija save(Operacija pregled) {
		return operacijaRepository.save(pregled);
		
	}

	public List<Operacija> findZavrsene() {
		// TODO Auto-generated method stub
		List<Operacija> sve=operacijaRepository.findAll();
		List<Operacija> zavrsene=new ArrayList<Operacija>();
		for (Operacija o : sve) {
			if (o.getDatum().before(new Date())) {
				zavrsene.add(o);
			}
		}
		return zavrsene;
	}

	public Operacija findOne(Integer id) {
		// TODO Auto-generated method stub
		return operacijaRepository.findById(id).orElseGet(null);
	}

	public List<Operacija> findNeZakazane() {
		// TODO Auto-generated method stub
		return operacijaRepository.findBySalaIsNotNull();
	}

	public List<Operacija> findZakazane() {
		// TODO Auto-generated method stub
		return operacijaRepository.findBySalaIsNull();
	}

}
