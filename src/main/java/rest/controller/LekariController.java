package rest.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.controller.PregledController.PreglediSale;
import rest.domain.Lekar;
import rest.domain.Operacija;
import rest.domain.Pregled;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.KlinikaDTO;
import rest.dto.LekarDTO;
import rest.dto.OperacijaDTO;
import rest.service.LekariService;
import rest.service.OperacijaService;
import rest.service.PregledService;


@RestController

@RequestMapping("rest/lekari")
public class LekariController {
	@Autowired
	
	private LekariService lekariService;
	@Autowired
	private PregledService pregledService;
	@Autowired
	private OperacijaService operacijaService;
	
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
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
	
	@GetMapping(value="/dostupni/{id}")
	public ResponseEntity<List<LekarDTO>> getDostupni(@PathVariable Integer id) {
		Operacija dto = operacijaService.findOne(id);
		for (Lekar lek: dto.getLekari())
			System.out.println("lekar za operaciju: " + lek.getIme() + " " + lek.getPrezime());
		System.out.println("operacija id: " + dto.getId());
		System.out.println("operacija datum: " + dto.getDatum());
		List<Lekar> lekari = lekariService.findAll();
		List<LekarDTO> lekariDTO = new ArrayList<>();
		first:
		for (Lekar l: lekari) {
			Date vremeKrajaOp = new Date(dto.getDatum().getTime());
			vremeKrajaOp.setTime(dto.getDatum().getTime() + dto.getTrajanje()*60000);
			List<Pregled> pregledi = pregledService.findZakazane(l);
			for (Pregled p: pregledi) {
				Date pregledTime = new Date(p.getDatum().getTime());
				pregledTime.setTime(p.getDatum().getTime() + p.getTrajanje()*60000);
				if (p.getDatum().after(dto.getDatum()) && p.getDatum().before(vremeKrajaOp)) {
					continue first;
				}
				else if (p.getDatum().before(dto.getDatum()) && pregledTime.after(dto.getDatum())) {
					continue first;
				}
			}
			List<Operacija> operacije = operacijaService.findNeZakazane();
			for (Operacija o: operacije) {
				Date operacijaTime = new Date(o.getDatum().getTime());
				operacijaTime.setTime(o.getDatum().getTime() + o.getTrajanje()*60000);
				if (o.getDatum().after(dto.getDatum()) && o.getDatum().before(vremeKrajaOp)) {
					if (o.getLekari().contains(l))
						continue first;
				}
				else if (o.getDatum().before(dto.getDatum()) && operacijaTime.after(dto.getDatum())) {
					if (o.getLekari().contains(l))
						continue first;
				}
			}
			LekarDTO lekar = new LekarDTO(l);
			lekar.setProsek(l);
			lekariDTO.add(lekar);
		}
		for (LekarDTO dt: lekariDTO) {
			System.out.println("lekar id: " + dt.getId());
			System.out.println("lekar: " + dt.getIme() + " " + dt.getPrezime());
		}
		return new ResponseEntity<>(lekariDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE && tipKorisnika()!=Uloga.LEKAR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// a course must exist
		Lekar lekar = lekariService.findOne(lekarDTO.getId());

		if (lekar == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("IDEMO");
		lekar.setPrviPut(lekarDTO.getPrviPut());
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
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		lekarDTO.setPrviPut(true);
		lekariService.addLekar(lekarDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping(value="/ocena",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LekarDTO> ocene(@RequestParam String lekar,@RequestParam String ocena,@RequestParam String pacijent){
		if(tipKorisnika()!=Uloga.PACIJENT) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		List<Pregled> svi = pregledService.findAll();
		boolean moze = false;
		for(Pregled p : svi) {
			if (p.getLekar().getEmail().equals(lekar)) {
				if(p.getKarton() != null) {
					if(p.getKarton().getPacijent().getEmail().equals(pacijent)) {
						moze = true;
					}
				}
			}
		}
		if(!moze) {
			LekarDTO lto = new LekarDTO();
			lto.setIme("Ne mozete oceniti lekara koji Vas nije pregledao!");
			return new  ResponseEntity<>(lto,HttpStatus.BAD_REQUEST);
		}
		l.getOcene().add(Integer.parseInt(ocena));
		lekariService.save(l);
		System.out.println("Ocena uspesno dodata");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


