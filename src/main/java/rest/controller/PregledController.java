package rest.controller;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

import rest.domain.AdministratorKlinike;
import rest.domain.Karton;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.Operacija;
import rest.domain.Pregled;
import rest.domain.Sala;
import rest.domain.StavkaCenovnika;
import rest.domain.TipPregleda;
import rest.domain.User;
import rest.dto.KartonDTO;
import rest.dto.LekarDTO;
import rest.dto.PregledDTO;
import rest.pk.SalaPK;
import rest.service.AdminKService;
import rest.service.KartonService;
import rest.service.KlinikaService;
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
	private KlinikaService klinikaService;
	@Autowired
	private LekariService lekarService;
	@Autowired
	private AdminKService akService;
	@Autowired
	private SalaService salaService;
	@Autowired
	private TipPregledaService tipPregledaService;
	@Autowired
	private StavkaCenovnikaService stavkaCenovnikaService;
	@Autowired
	private KartonService kartonService;

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
	}
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
		System.out.println("kiko2928");
		Lekar l=lekarService.findOne(id);
		List<Pregled> sltermini = pregledService.findZakazane(l);
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			slterminiDTO.add(new PregledDTO(s));
			System.out.println("sasa23343");
		}
		System.out.println("kiko332");
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
		Date pPoslije=p.getDatum();
		pPoslije.setTime(pPoslije.getTime()+ p.getTrajanje()*60000);
		Lekar l=lekarService.findOne(pregledDTO.getLekar().getId());
		boolean mozel=true;
		for (Pregled pr : l.getPregledi()) {
			Date prPoslije=pr.getDatum();
			prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
			if((p.getDatum().after(pr.getDatum()) && p.getDatum().before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
				mozel=false;
				break;
			}
		}
		if(mozel==true) {
			for (Operacija pr : l.getOperacije()) {
				Date prPoslije=pr.getDatum();
				prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
				if((p.getDatum().after(pr.getDatum()) && p.getDatum().before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
					mozel=false;
					break;
				}
			}	
		}
		boolean ima=false;
		if(mozel==false) {
			System.out.println("Doktor je zauzet");
			for (Lekar lek: k.getLekari()) {
				if( lek.getTipPregleda().getId()==p.getTip().getId() && JelSlobodanLekar(lek,p.getDatum(),pPoslije)==true) {
					p.setLekar(lek);
					ima=true;
					break;
				}
			}
		}
		if(ima==false) {
			System.out.println("Doktori su svi zauzeti");
			p=algoritamPregled(p,k,p.getTip().getId(),p.getDatum(),pPoslije);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pregledService.save(p);
		System.out.println("saa");
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	private Pregled algoritamPregled(Pregled pregled,Klinika k, Integer idTipa, Date pocetak, Date pPoslije) {
		Sala najboljaSala=null;
		Date najboljiPocetak= new Date();
		Lekar slLekar=null;
		List<Sala> sale=salaService.findAll(k);
		for (Sala sala : sale) {
			boolean mozel=true;
			for (Pregled pr : sala.getPregledi()) {
				Date prPoslije=pr.getDatum();
				prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
				if((pocetak.after(pr.getDatum()) && pocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
					for (Operacija op : sala.getOperacije()) {
						Date opPoslije=op.getDatum();
						opPoslije.setTime(opPoslije.getTime()+ op.getTrajanje()*60000);
						if((pocetak.after(op.getDatum()) && pocetak.before(opPoslije))|| (pPoslije.after(op.getDatum()) && pPoslije.before(prPoslije))) {
							for (Lekar lek: k.getLekari()) {
								if( lek.getTipPregleda().getId()==idTipa && JelSlobodanLekar(lek,pocetak,pPoslije)==true) {
									pregled.setDatum(pocetak);
									pregled.setSala(sala);
									pregled.setLekar(lek);
									return pregled;
								}
							}
						}
					}
				}else if(pocetak.before(pr.getDatum()) && (pr.getDatum().getTime()-prPoslije.getTime()<=60000*pregled.getTrajanje())) {
					for (Operacija op : sala.getOperacije()) {
						Date opPoslije=op.getDatum();
						opPoslije.setTime(opPoslije.getTime()+ op.getTrajanje()*60000);
						if((pr.getDatum().after(op.getDatum()) && pr.getDatum().before(opPoslije))|| (prPoslije.after(op.getDatum()) && prPoslije.before(prPoslije))) {
							for (Lekar lek: k.getLekari()) {
								if( lek.getTipPregleda().getId()==idTipa && JelSlobodanLekar(lek,pr.getDatum(),prPoslije)==true) {
									if(najboljiPocetak.getTime()>pr.getDatum().getTime()) {
										najboljiPocetak=pr.getDatum();
										najboljaSala=sala;
										slLekar=lek;
									}
								}
							}
						}
					}
				}
			}
		}
		pregled.setDatum(najboljiPocetak);
		pregled.setLekar(slLekar);
		pregled.setSala(najboljaSala);
		return pregled;		
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
	}
	@PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody PregledDTO pregledDTO){
		StavkaCenovnika st=stavkaCenovnikaService.findOne(pregledDTO.getCena().getId());
		Lekar l=lekarService.findOne(pregledDTO.getLekar().getId());
		Sala s=null;
		TipPregleda t=null;
		try {
			SalaPK pk=new SalaPK(pregledDTO.getSala().getBrojSale(),pregledDTO.getSala().getKlinika());
			t=tipPregledaService.findOne(pregledDTO.getTip().getId());
			s=salaService.findOne(pk);
		} catch (Exception e) {
			System.out.println("nema sale");
		}
		Karton k= null;
		try {
			KartonDTO kd=pregledDTO.getKarton();
			System.out.println(kd.getId()+" to je karton id");
			k=kartonService.findOne(kd.getId());
		}catch (Exception e) {
			System.out.println("nema kartona");
		}
		Pregled pregled=new Pregled(pregledDTO,st,l,s,t,k);
		pregledService.save(pregled);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	private boolean JelSlobodanLekar(Lekar l, Date pPocetak, Date pPoslije) {
		boolean mozel=true;
		for (Pregled pr : l.getPregledi()) {
			Date prPoslije=pr.getDatum();
			prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
			if((pPocetak.after(pr.getDatum()) && pPocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
				return false;
			}
		}
		if(mozel==true) {
			for (Operacija pr : l.getOperacije()) {
				Date prPoslije=pr.getDatum();
				prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
				if((pPocetak.after(pr.getDatum()) && pPocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
					return false;
				}
			}	
		}
		return true;
	}
}
