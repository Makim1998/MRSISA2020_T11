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

import rest.domain.Lekar;
import rest.domain.Pregled;
import rest.domain.Sala;
import rest.domain.StavkaCenovnika;
import rest.domain.TipPregleda;
import rest.domain.User;
import rest.dto.PregledDTO;
import rest.pk.SalaPK;
import rest.service.LekariService;
import rest.service.PregledService;
import rest.service.SalaService;
import rest.service.StavkaCenovnikaService;
import rest.service.TipPregledaService;

@RestController
@RequestMapping("rest/Pregled")
public class PregledController {
	@Autowired
	private PregledService pregledService;
	@Autowired
	private LekariService lekarService;
	@Autowired
	private SalaService salaService;
	@Autowired
	private TipPregledaService tipPregledaService;
	@Autowired
	private StavkaCenovnikaService stavkaCenovnikaService;

	@GetMapping
	(value="/slobodni")
	public ResponseEntity<List<PregledDTO>> getSlobodniTerminiPregleda() {
		System.out.println("kiko1");
		List<Pregled> sltermini = pregledService.findSlobodne();
		System.out.println("kiko2");
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			slterminiDTO.add(new PregledDTO(s));
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}/*
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<TipPregledaDTO> updateCourse(@RequestBody TipPregledaDTO tipPregledaDTO) {

		// a course must exist
		TipPregleda tipPregleda = tipPregledaService.findOne(tipPregledaDTO.getId());

		if (tipPregleda == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		tipPregleda.setNaziv(tipPregledaDTO.getNaziv());

		tipPregleda = tipPregledaService.save(tipPregleda);
		return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.OK);
	}*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {

		Pregled pregled = pregledService.findOne(id);
		System.out.println("brisanje");
		if (pregled != null) {
			pregledService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody PregledDTO pregledDTO){
		StavkaCenovnika st=stavkaCenovnikaService.findOne(pregledDTO.getCena().getId());
		Lekar l=lekarService.findOne(pregledDTO.getLekar().getId());
		SalaPK pk=new SalaPK(pregledDTO.getSala().getBrojSale(),pregledDTO.getSala().getKlinika());
		Sala s=salaService.findOne(pk);
		TipPregleda t=tipPregledaService.findOne(pregledDTO.getTip().getId());
		Pregled pregled=new Pregled(pregledDTO,st,l,s,t);
		pregledService.save(pregled);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
