package rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Cenovnik;
import rest.domain.Klinika;
import rest.domain.StavkaCenovnika;
import rest.repository.CenovnikRepository;
import rest.repository.KlinikaRepository;


@Service 

public class CenovnikService {

	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	

	public Cenovnik findOneByKlinika(Integer id) {
		Optional<Klinika> optklinika= klinikaRepository.findById(id);
		Klinika klinika=optklinika.get();
		return cenovnikRepository.findOneByKlinika(klinika);
	}

}
