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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.TipPregleda;
import rest.domain.User;
import rest.dto.TipPregledaDTO;
import rest.service.TipPregledaService;

@RestController
@RequestMapping("rest/tipPregleda")
public class TipPregledaController {
	@Autowired
	private TipPregledaService tipPregledaService;

	@GetMapping
	public ResponseEntity<List<TipPregledaDTO>> getTipPregleda() {
		
		List<TipPregleda> tipovi = tipPregledaService.findAll();

		List<TipPregledaDTO> tipoviDTO = new ArrayList<>();
		for (TipPregleda s : tipovi) {
			tipoviDTO.add(new TipPregledaDTO(s));
		}

		return new ResponseEntity<>(tipoviDTO, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

		TipPregleda tip = tipPregledaService.findOne(id);
		System.out.println("brisanje");
		if (tip != null) {
			tipPregledaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody TipPregledaDTO tipPregledaDTO){
		TipPregleda tipPregleda=new TipPregleda(tipPregledaDTO.getId(),tipPregledaDTO.getNaziv());
		tipPregledaService.save(tipPregleda);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
