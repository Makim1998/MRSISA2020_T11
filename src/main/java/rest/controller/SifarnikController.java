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

import rest.domain.StavkaSifarnika;
import rest.dto.StavkaSifarnikaDTO;
import rest.service.StavkaSifarnikaService;

@RestController
@RequestMapping("rest/sifarnik")
public class SifarnikController {
	
	@Autowired
	private StavkaSifarnikaService service;
	
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
		StavkaSifarnika stavka = new StavkaSifarnika(dto);
		stavka = service.save(stavka);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value="/izmeni",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StavkaSifarnikaDTO> izmeni(@RequestBody StavkaSifarnikaDTO dto){
		StavkaSifarnika stavka = service.findOne(dto.getId());
		if (stavka == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		stavka.setSifra(dto.getSifra());
		stavka.setStavkaId(dto.getStavkaId());
		stavka.setTip(dto.getTip());
		stavka = service.save(stavka);
		return new ResponseEntity<>(new StavkaSifarnikaDTO(stavka), HttpStatus.OK);
	}
}
