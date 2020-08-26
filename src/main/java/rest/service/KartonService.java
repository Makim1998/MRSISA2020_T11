package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Karton;
import rest.dto.KartonDTO;
import rest.repository.KartonRepository;

@Service 
public class KartonService {

	@Autowired
	private KartonRepository kartonRepository;

	public Karton findOne(Integer id) {
		// TODO Auto-generated method stub
		return kartonRepository.findById(id).orElseGet(null);
	}
	
	public Karton save(Karton karton) {
		return kartonRepository.save(karton);
	}
}
