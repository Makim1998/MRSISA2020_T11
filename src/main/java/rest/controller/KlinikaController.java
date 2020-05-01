package rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Klinika;
import rest.dto.KlinikaDTO;
import rest.service.KlinikaService;

@RestController
@RequestMapping("rest/klinika")
public class KlinikaController {
	
	@Autowired
	private KlinikaService service;
	
	@GetMapping
	public ResponseEntity<List<KlinikaDTO>> getKlinike(){
		List<Klinika> klinike = service.findAll();
		List<KlinikaDTO> ret = new ArrayList<KlinikaDTO>();
		for (Klinika k: klinike) {
			KlinikaDTO dto = new KlinikaDTO(k);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<KlinikaDTO> updateCourse(@RequestBody KlinikaDTO kDTO) {

		System.out.println("IDEMO1");
		Klinika k = service.findOne(kDTO.id);

		if (k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		k.setNaziv(kDTO.naziv);
		k.setAdresa(kDTO.adresa);
		k.setOpis(kDTO.opis);
		k= service.save(k);
		return new ResponseEntity<>(new KlinikaDTO(k), HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Klinika> dodajKlinika(@RequestBody KlinikaDTO klinikaDto){
		Klinika klinika = new Klinika(klinikaDto);
		service.save(klinika);
		return new ResponseEntity<>(klinika, HttpStatus.OK);
	}
	
	@GetMapping(value="/ids")
	public ResponseEntity<List<Integer>> getIds(){
		List<Klinika> klinike = service.findAll();
		List<Integer> ids = new ArrayList<Integer>();
		for (Klinika k: klinike) {
			ids.add(k.getId());
		}
		return new ResponseEntity<>(ids, HttpStatus.OK);
	}

}
