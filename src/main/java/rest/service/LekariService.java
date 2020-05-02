
package rest.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.dto.LekarDTO;
import rest.repository.KlinikaRepository;
import rest.repository.LekarRepository;

@Service
public class LekariService {
		
	@Autowired
	private LekarRepository lekarRepository;
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public Lekar findOne(Integer id) {
		return lekarRepository.findById(id).orElseGet(null);
	}

	public Lekar findByEmail(String email) {
		return lekarRepository.findOneByEmail(email);
	}

	public List<Lekar> findAll() {
		return lekarRepository.findAll();
	}

	public Lekar save(Lekar lekar) {
		return lekarRepository.save(lekar);
	}

	public void remove(Integer id) {
	lekarRepository.deleteById(id);
	}

	public void addLekar(LekarDTO lekarDTO) throws ParseException {
		Lekar lekar=new Lekar(lekarDTO);
		Optional<Klinika> optklinika=klinikaRepository.findById(lekarDTO.getKc_id());
		Klinika klinika=optklinika.get();
		lekar.setKlinika(klinika);
		lekarRepository.save(lekar);
	}

	}
