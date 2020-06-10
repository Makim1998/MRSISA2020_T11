package rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

import rest.domain.AdministratorKlinike;
import rest.domain.Karton;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.Operacija;
import rest.domain.Pregled;
import rest.domain.Sala;
import rest.domain.StavkaCenovnika;
import rest.domain.TipPregleda;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.KartonDTO;
import rest.dto.LekarDTO;
import rest.dto.OperacijaDTO;
import rest.dto.PregledDTO;
import rest.pk.SalaPK;
import rest.service.AdminKService;
import rest.service.KartonService;
import rest.service.KlinikaService;
import rest.service.LekariService;
import rest.service.MailService;
import rest.service.OperacijaService;
import rest.service.PregledService;
import rest.service.SalaService;
import rest.service.StavkaCenovnikaService;
import rest.service.TipPregledaService;

@RestController
@RequestMapping("rest/Operacija")
public class OperacijaController {
	@Autowired
	private OperacijaService operacijaService;
	@Autowired
	private KlinikaService klinikaService;
	@Autowired
	private LekariService lekarService;
	@Autowired
	private AdminKService akService;
	@Autowired
	private SalaService salaService;
	@Autowired
	private KartonService kartonService;
	@Autowired
	private TipPregledaService tipPregledaService;
	@Autowired
	private StavkaCenovnikaService stavkaCenovnikaService;
	@Autowired
	private MailService mailService;
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
/*
	@GetMapping
	(value="/slobodni")
	public ResponseEntity<List<PregledDTO>> getSlobodniTerminiPregleda() {
		List<Pregled> sltermini = pregledService.findSlobodne();
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			slterminiDTO.add(new PregledDTO(s));
		}
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}*//*
	@GetMapping
	(value="/zaPacijenta")
	public ResponseEntity<List<PregledDTO>> getPreglediZaPacijenta(@RequestParam String email) {
		System.out.println("pregledi za pacijenta");
		List<Pregled> svi = pregledService.findAll();
		List<PregledDTO> zaPacijenta = new ArrayList<>();
		for (Pregled p : svi) {
			if(p.getKarton() != null ) {
				if(email.equals(p.getKarton().getPacijent().getEmail())) {
					zaPacijenta.add(new PregledDTO(p));
					System.out.println(p.getId());
				}
			}
		}
		System.out.println(email);
		System.out.println(zaPacijenta.size());
		return new ResponseEntity<>(zaPacijenta, HttpStatus.OK);
	}
	
	@GetMapping
	(value = "/zakazani/{id}")
	public ResponseEntity<List<PregledDTO>> getZakazaniTerminiPregleda(@PathVariable Integer id) throws ParseException {
		System.out.println("kiko1");
		Lekar l=lekarService.findOne(id);
		List<Pregled> sltermini = pregledService.findZakazane(l);
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			slterminiDTO.add(new PregledDTO(s));
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	@GetMapping
	(value = "/zavrseni/{id}")
	public ResponseEntity<List<PregledDTO>> getZavrseniTerminiPregleda(@PathVariable Integer id) throws ParseException {
		List<Pregled> sltermini = pregledService.findZavrsene();
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<PregledDTO>();
		for (Pregled s : sltermini) {
			if(s.getLekar().getKlinika().getId()==id) {
				slterminiDTO.add(new PregledDTO(s));
			}
		}
		Collections.sort(slterminiDTO);
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	@GetMapping
	(value = "/zahtevi/{id}")
	public ResponseEntity<List<PregledDTO>> getZahteviregleda(@PathVariable Integer id) throws ParseException {
		System.out.println("kiko1");
		AdministratorKlinike ak=akService.findOne(id);
		List<Pregled> sltermini = pregledService.findZakazane();
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			System.out.println("kyle");
			slterminiDTO.add(new PregledDTO(s));
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	@GetMapping
	(value = "/nezahtevi/{id}")
	public ResponseEntity<List<PregledDTO>> getNeZahteviregleda(@PathVariable Integer id) throws ParseException {
		System.out.println("kiko1");
		AdministratorKlinike ak=akService.findOne(id);
		List<Pregled> sltermini = pregledService.findNeZakazane();
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			System.out.println("kyle");
			slterminiDTO.add(new PregledDTO(s));
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	@GetMapping
	(value="/svi")
	public ResponseEntity<List<PregledDTO>> getSviPregledi() {
		List<Pregled> svi = pregledService.findAll();
		List<PregledDTO> sviDTO = new ArrayList<>();
		for (Pregled s : svi) {
			sviDTO.add(new PregledDTO(s));
			System.out.println("imaam");
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(sviDTO, HttpStatus.OK);
	}
	@PutMapping(value="/potvrdi",consumes = "application/json")
	public ResponseEntity<PregledDTO> updateCourse(@RequestBody PregledDTO pregledDTO) {

		// a course must exist
		Pregled p = pregledService.findOne(pregledDTO.getId());

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println(pregledDTO.getSala().getBrojSale());
		System.out.println(pregledDTO.getSala().getKlinika());
		Klinika k= klinikaService.findOne(pregledDTO.getSala().getKlinika());
		Sala s=new Sala(k,pregledDTO.getSala().getBrojSale(),pregledDTO.getSala().getNaziv());
		p.setSala(s);
		p.setDatum(pregledDTO.getDatum());
		System.out.println("15. MAJ");
		pregledService.save(p);
		System.out.println("saa");
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
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
	}*/
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> dodajOperaciju(@RequestBody OperacijaDTO operacijaDTO){
		if(tipKorisnika()!=Uloga.LEKAR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		StavkaCenovnika st=stavkaCenovnikaService.findOne(operacijaDTO.getCena().getId());
		//ArrayList<Lekari>
		HashSet<Lekar> lekari = new HashSet<Lekar>();
		Klinika klinika=null;
		for (LekarDTO le : operacijaDTO.getLekari()) {
			Lekar l=lekarService.findOne(le.getId());
			klinika=l.getKlinika();
			lekari.add(l);
		}
		Sala s=null;
		TipPregleda t=null;
		try {
			SalaPK pk=new SalaPK(operacijaDTO.getSala().getBrojSale(),operacijaDTO.getSala().getKlinika());
			//t=tipPregledaService.findOne(operacijaDTO.getTip().getId());
			s=salaService.findOne(pk);
		} catch (Exception e) {
			System.out.println("nema sale");
		}
		Karton k= null;
		try {
			KartonDTO kd=operacijaDTO.getKarton();
			k=kartonService.findOne(kd.getId());
		}catch (Exception e) {
			System.out.println("nema kartona");
		}
		Operacija pregled=new Operacija(operacijaDTO,st,s,lekari,k);
		operacijaService.save(pregled);
		if(s==null) {
			AdministratorKlinike ak=akService.findByKlinika(klinika);
			String mail=ak.getEmail();
			String naslov="Zakazivanje operacije";
			String tekst="Poštovani,"
					+ "\nNova operacija zakazana je za "+pregled.getDatum().toString().substring(0,16)
			        + "\nMolimo vas da rezervisete salu u toku dana.";
			mailService.SendMail(mail, naslov, tekst);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

