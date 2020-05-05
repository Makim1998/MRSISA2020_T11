package rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import rest.domain.MedicinskaSestra;
import rest.domain.User;
import rest.dto.MedicinskaSestraDTO;
import rest.service.KlinikaService;
import rest.service.MSService;

@RestController
@RequestMapping("rest/medSestre")
public class MSController {
	
	@Autowired
	private MSService service;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@GetMapping
	public ResponseEntity<List<MedicinskaSestraDTO>> findAll(){
		List<MedicinskaSestra> sestre = service.findAll();
		List<MedicinskaSestraDTO> ret = new ArrayList<>();
		for (MedicinskaSestra ms : sestre) {
			MedicinskaSestraDTO dto = new MedicinskaSestraDTO(ms);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<MedicinskaSestraDTO> updateCourse(@RequestBody MedicinskaSestraDTO dto){
		MedicinskaSestra ms = service.findOne(dto.getId());
		ms.setAdresa(dto.getAdresa());
		ms.setDrzava(dto.getDrzava());
		ms.setGrad(dto.getGrad());
		ms.setIme(dto.getIme());
		ms.setPassword(dto.getPassword());
		ms.setPrezime(dto.getPrezime());
		ms.setPrviPut(dto.getPrviPut());
		ms = service.save(ms);
		return new ResponseEntity<>(new MedicinskaSestraDTO(ms), HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody MedicinskaSestraDTO dto) throws ParseException{
		dto.setPrviPut(true);
		MedicinskaSestra ms = new MedicinskaSestra(dto);
		Klinika klinika = klinikaService.findOne(dto.getKc_id());
		ms.setKlinika(klinika);
		ms = service.save(ms);
		Set<MedicinskaSestra> sestre = klinika.getMedicinskeSestre();
		sestre.add(ms);
		klinika.setMedicinskeSestre(sestre);
		klinika = klinikaService.save(klinika);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
