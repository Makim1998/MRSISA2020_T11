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

import rest.domain.Klinika;
import rest.domain.Pregled;
import rest.domain.TipPregleda;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.TipPregledaDTO;
import rest.service.KlinikaService;
import rest.service.PregledService;
import rest.service.TipPregledaService;

@RestController
@RequestMapping("rest/tipPregleda")
public class TipPregledaController {
	@Autowired
	private TipPregledaService tipPregledaService;
	@Autowired
	private KlinikaService klinikaService;
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}

	@GetMapping
	public ResponseEntity<List<TipPregledaDTO>> getTipPregleda() {
		
		List<TipPregleda> tipovi = tipPregledaService.findAll();
		Integer a=0;
		List<TipPregledaDTO> tipoviDTO = new ArrayList<>();
		for (TipPregleda s : tipovi) {
			a=s.getKlinika().getId();
			tipoviDTO.add(new TipPregledaDTO(s,a));
		}

		return new ResponseEntity<>(tipoviDTO, HttpStatus.OK);
	}
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<TipPregledaDTO> updateCourse(@RequestBody TipPregledaDTO tipPregledaDTO) {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// a course must exist
		TipPregleda tipPregleda = tipPregledaService.findOne(tipPregledaDTO.getId());

		if (tipPregleda == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		tipPregleda.setNaziv(tipPregledaDTO.getNaziv());

		tipPregleda = tipPregledaService.save(tipPregleda);
		Integer a=tipPregleda.getKlinika().getId();
		return new ResponseEntity<>(new TipPregledaDTO(tipPregleda,a), HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		TipPregleda tip = tipPregledaService.findOne(id);
		
		List<Pregled> ztermini = pregledService.findZauzete(tip);
		if (ztermini.isEmpty()){
			if (tip != null) {
				tipPregledaService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody TipPregledaDTO tipPregledaDTO){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Klinika k=klinikaService.findOne(tipPregledaDTO.getKlinika());
		TipPregleda tipPregleda=new TipPregleda(tipPregledaDTO.getId(),tipPregledaDTO.getNaziv(),k);
		tipPregledaService.save(tipPregleda);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
