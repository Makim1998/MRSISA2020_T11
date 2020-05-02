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

import rest.domain.Cenovnik;
import rest.domain.Pregled;
import rest.domain.StavkaCenovnika;
import rest.domain.TipPregleda;
import rest.domain.User;
import rest.dto.CenovnikDTO;
import rest.dto.StavkaCenovnikaDTO;
import rest.service.CenovnikService;
import rest.service.PregledService;
import rest.service.StavkaCenovnikaService;


@RestController

@RequestMapping("rest/cenovnik")
public class CenovnikController {
	@Autowired
	private PregledService pregledService;
	@Autowired
	private CenovnikService cenovnikService;
	@Autowired
	private StavkaCenovnikaService stavkaCenovnikaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CenovnikDTO> getCenovnik(@PathVariable Integer id) {
		System.out.println("aaa2");
		
		Cenovnik cenovnik = cenovnikService.findOneByKlinika(id);
		System.out.println("AA "+cenovnik.getKlinika().getId());

		CenovnikDTO cenovnikDTO = new CenovnikDTO(cenovnik);

		return new ResponseEntity<CenovnikDTO>(cenovnikDTO, HttpStatus.OK);
	}
	@PutMapping(value="/izmeniStavku",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StavkaCenovnikaDTO> updateCourse(@RequestBody StavkaCenovnikaDTO stavkaCenovnikaDTO) {

		StavkaCenovnika stavka= stavkaCenovnikaService.findOne(stavkaCenovnikaDTO.getId());

		if (stavka == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		stavka.setCena(stavkaCenovnikaDTO.getCena());
		stavka.setUsluga(stavkaCenovnikaDTO.getUsluga());

		stavka = stavkaCenovnikaService.save(stavka);
		return new ResponseEntity<>(new StavkaCenovnikaDTO(stavka), HttpStatus.OK);
	}
	@DeleteMapping(value = "/obrisiStavku/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
		StavkaCenovnika stavka= stavkaCenovnikaService.findOne(id);
		List<Pregled> ztermini = pregledService.findZauzete(stavka);
		if (ztermini.isEmpty()){
			System.out.println("brisanje");
			if (stavka != null) {
				stavkaCenovnikaService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			System.out.println("sds");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/dodajStavku",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> dodajStavku(@RequestBody StavkaCenovnikaDTO stavkaCenovnikaDTO){
		Integer id= stavkaCenovnikaDTO.getC_id();
		Cenovnik cenovnik=cenovnikService.findOne(stavkaCenovnikaDTO.getC_id());
		StavkaCenovnika stavka= new StavkaCenovnika(stavkaCenovnikaDTO,cenovnik);
		stavkaCenovnikaService.save(stavka);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


