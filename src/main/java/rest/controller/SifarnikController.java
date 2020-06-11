package rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.StavkaSifarnika;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.StavkaSifarnikaDTO;
import rest.service.StavkaSifarnikaService;

@RestController
@RequestMapping("rest/sifarnik")
public class SifarnikController {
	
	@Autowired
	private StavkaSifarnikaService service;
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
	@GetMapping
	public ResponseEntity<List<StavkaSifarnikaDTO>> getAll(){
		List<StavkaSifarnika> stavke = service.findAll();
		List<StavkaSifarnikaDTO> ret = new ArrayList<StavkaSifarnikaDTO>();
		for (StavkaSifarnika s: stavke) {
			StavkaSifarnikaDTO dto = new StavkaSifarnikaDTO(s);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addStavka(@RequestBody StavkaSifarnikaDTO dto){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		StavkaSifarnika stavka = new StavkaSifarnika(dto);
		stavka = service.save(stavka);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}/{novaSifra}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StavkaSifarnikaDTO> izmeni(@PathVariable Integer id, @PathVariable String novaSifra){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		StavkaSifarnika stavka = service.findOne(id);
		stavka.setSifra(novaSifra);
		stavka = service.save(stavka);
		return new ResponseEntity<>(new StavkaSifarnikaDTO(stavka), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> obrisi(@PathVariable Integer id){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		service.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
