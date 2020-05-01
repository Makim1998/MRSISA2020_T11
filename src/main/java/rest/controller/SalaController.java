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

import rest.domain.Klinika;
import rest.domain.Pregled;
import rest.domain.Sala;
import rest.domain.User;
import rest.dto.SalaDTO;
import rest.pk.SalaPK;
import rest.service.KlinikaService;
import rest.service.PregledService;
import rest.service.SalaService;


@RestController

@RequestMapping("rest/sala")
public class SalaController {
	@Autowired
	private SalaService salaService;
	@Autowired
	private KlinikaService klinikaService;
	@Autowired
	private PregledService pregledService;
	@GetMapping
	public ResponseEntity<List<SalaDTO>> getSala() {
		System.out.println("prvi maj");
		List<Sala> sale = salaService.findAll();
		System.out.println("1.maj: "+sale.isEmpty());
		List<SalaDTO> saleDTO = new ArrayList<>();
		for (Sala s : sale) {
			saleDTO.add(new SalaDTO(s.getId(),s.getKlinika().getId(),s.getNaziv()));
		}
		
		System.out.println("top");
		return new ResponseEntity<>(saleDTO, HttpStatus.OK);
	}
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<SalaDTO> updateCourse(@RequestBody SalaDTO salaDTO) {

		// a course must exis
		SalaPK pk=new SalaPK(salaDTO.getBrojSale(),salaDTO.getKlinika());
		Sala sala = salaService.findOne(pk);

		if (sala == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		sala.setNaziv(salaDTO.getNaziv());

		sala = salaService.save(sala);
		return new ResponseEntity<>(new SalaDTO(sala.getId(),sala.getKlinika().getId(),sala.getNaziv()), HttpStatus.OK);
	}
	@PostMapping(value = "/obrisi")
	public ResponseEntity<Void> deleteCourse(@RequestBody SalaPK id) {
		Sala sala = salaService.findOne(id);
		System.out.println("brisanje");
		List<Pregled> ztermini = pregledService.findZauzete(sala);
		System.out.println(ztermini.isEmpty());
		if (ztermini.isEmpty()){
			System.out.println("brisanje");
			if (sala != null) {
				salaService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> dodajSalu(@RequestBody SalaDTO salaDTO){
		//SalaPK pk=new SalaPK(salaDTO.getBrojSale(),salaDTO.getKlinika());
		Klinika k= klinikaService.findOne(salaDTO.getKlinika());
		/*SalaPK pk=new SalaPK(salaDTO.getBrojSale(),salaDTO.getKlinika());
		Sala salab = salaService.findOne(pk);
		if (salab != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}*/
		
		Sala sala= new Sala(k,salaDTO.getBrojSale(),salaDTO.getNaziv());
		salaService.save(sala);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

