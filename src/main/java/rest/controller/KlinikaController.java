package rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			dto.setProsek(k);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Klinika> dodajKlinika(@RequestBody KlinikaDTO klinikaDto){
		Klinika klinika = new Klinika(klinikaDto);
		service.save(klinika);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value="/ocena",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KlinikaDTO> ocene(@RequestParam String klinika,@RequestParam String ocena){
		Klinika k = service.findByNaziv(klinika);
		System.out.println("Ocenjivanje klinike");
		System.out.println(ocena);
		System.out.println(klinika);
		if(k == null) {
			System.out.println("Ne postoji klinika");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(ocena.equals("")) {
			System.out.println("Ocena nevalidna");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		k.getOcene().add(Integer.parseInt(ocena));
		service.save(k);
		System.out.println("Ocena uspesno dodata");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
