package rest.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Lekar;
import rest.domain.Pregled;
import rest.domain.User;
import rest.dto.LekarDTO;
import rest.service.LekariService;
import rest.service.PregledService;


@RestController

@RequestMapping("rest/lekari")
public class LekariController {
	@Autowired
	
	private LekariService lekariService;
	@Autowired
	private PregledService pregledService;
	
	@GetMapping
	public ResponseEntity<List<LekarDTO>> getLekari() {
		
		List<Lekar> lekari = lekariService.findAll();

		List<LekarDTO> lekariDTO = new ArrayList<>();
		for (Lekar s : lekari) {
			LekarDTO l = new LekarDTO(s);
			l.setProsek(s);
			lekariDTO.add(l);
			
		}

		return new ResponseEntity<>(lekariDTO, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {

		Lekar lekar = lekariService.findOne(id);
		List<Pregled> ztermini = pregledService.findZauzete(lekar);
		if (ztermini.isEmpty()){
			System.out.println("brisanje");
			if (lekar != null) {
				lekariService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<LekarDTO> updateCourse(@RequestBody LekarDTO lekarDTO) {

		// a course must exist
		Lekar lekar = lekariService.findOne(lekarDTO.getId());

		if (lekar == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("IDEMO");
		lekar.setIme(lekarDTO.getIme());
		lekar.setPrezime(lekarDTO.getPrezime());
		lekar.setAdresa(lekarDTO.getAdresa());
		lekar.setGrad(lekarDTO.getGrad());
		lekar.setDrzava(lekarDTO.getDrzava());
		lekar.setPassword(lekarDTO.getPassword());

		lekar = lekariService.save(lekar);
		return new ResponseEntity<>(new LekarDTO(lekar), HttpStatus.OK);
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody LekarDTO lekarDTO) throws ParseException{
		lekariService.addLekar(lekarDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping(value="/ocena",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LekarDTO> ocene(@RequestParam String lekar,@RequestParam String ocena){
		Lekar l = lekariService.findByEmail(lekar);
		System.out.println("Ocenjivanje lekara");
		System.out.println(ocena);
		System.out.println(lekar);
		if(l == null) {
			System.out.println("Ne postoji lekar");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(ocena.equals("")) {
			System.out.println("Ocena nevalidna");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		l.getOcene().add(Integer.parseInt(ocena));
		lekariService.save(l);
		System.out.println("Ocena uspesno dodata");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


