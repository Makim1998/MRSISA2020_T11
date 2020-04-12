package rest.controller;

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
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Sala;
import rest.domain.SalaDTO;
import rest.domain.User;
import rest.service.SalaService;


@RestController

@RequestMapping("rest/sala")
public class SalaController {
	@Autowired
	
	private SalaService salaService;

	@GetMapping
	public ResponseEntity<List<SalaDTO>> getSala() {
		
		List<Sala> sale = salaService.findAll();

		List<SalaDTO> saleDTO = new ArrayList<>();
		for (Sala s : sale) {
			saleDTO.add(new SalaDTO(s));
		}

		return new ResponseEntity<>(saleDTO, HttpStatus.OK);
	}
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<SalaDTO> updateCourse(@RequestBody SalaDTO salaDTO) {

		// a course must exist
		Sala sala = salaService.findOne(salaDTO.getId());

		if (sala == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		sala.setNaziv(salaDTO.getNaziv());

		sala = salaService.save(sala);
		return new ResponseEntity<>(new SalaDTO(sala), HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {

		Sala sala = salaService.findOne(id);
		System.out.println("brisanje");
		if (sala != null) {
			salaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody SalaDTO salaDTO){
		Sala sala= new Sala(salaDTO.getId(),salaDTO.getNaziv());
		salaService.save(sala);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

