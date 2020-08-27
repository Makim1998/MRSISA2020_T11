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

import rest.domain.Dijagnoza;
import rest.domain.Lek;
import rest.domain.StavkaSifarnika;
import rest.domain.TipSifre;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.StavkaSifarnikaDTO;
import rest.service.DijagnozaService;
import rest.service.LekService;
import rest.service.StavkaSifarnikaService;

@RestController
@RequestMapping("rest/sifarnik")
public class SifarnikController {
	
	@Autowired
	private StavkaSifarnikaService service;
	@Autowired
	private DijagnozaService dijagnozaService;
	@Autowired
	private LekService lekService;
	
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
		for (StavkaSifarnika ss: service.findAll()) {
			if (ss.getSifra().equals(dto.getSifra()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			if (ss.getStavkaId() == dto.getStavkaId() && ss.getTip() == TipSifre.valueOf(dto.getTip()))
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
		for (StavkaSifarnika ss: service.findAll()) {
			if (ss.getSifra().equals(novaSifra))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
	
	@GetMapping(value="/dijagnoze")
	public ResponseEntity<List<String>> getDijagnoze(){
		if(tipKorisnika()!=Uloga.LEKAR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<String> ret = new ArrayList<String>();
		for (StavkaSifarnika ss: service.findAll()) {
			if (ss.getTip() == TipSifre.DIJAGNOZA) {
				if (dijagnozaService.findOne(ss.getStavkaId()) != null) {
					Dijagnoza dijagnoza = dijagnozaService.findOne(ss.getStavkaId());
					String str = ss.getSifra() + " - " + dijagnoza.getOpis();
					ret.add(str);
				}
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping(value="/lekovi")
	public ResponseEntity<List<String>> getLekovi(){
		if(tipKorisnika()!=Uloga.LEKAR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<String> ret = new ArrayList<String>();
		for (StavkaSifarnika ss: service.findAll()) {
			if (ss.getTip() == TipSifre.LEK) {
				if (lekService.findOne(ss.getStavkaId()) != null) {
					Lek lek = lekService.findOne(ss.getStavkaId());
					String str = ss.getSifra() + " - " + lek.getNaziv();
					ret.add(str);
				}
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
