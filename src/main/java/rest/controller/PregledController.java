package rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import rest.domain.GodisnjiOdmor;
import rest.domain.AdministratorKlinike;
import rest.domain.Karton;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.Operacija;
import rest.domain.Pacijent;
import rest.domain.Pregled;
import rest.domain.Sala;
import rest.domain.StavkaCenovnika;
import rest.domain.TipPregleda;
import rest.domain.User;

import rest.dto.KartonDTO;
import rest.dto.LekarDTO;
import rest.dto.PregledDTO;
import rest.dto.SalaDTO;
import rest.pk.SalaPK;
import rest.service.AdminKService;
import rest.service.KartonService;
import rest.service.KlinikaService;
import rest.service.LekariService;
import rest.service.MailService;
import rest.service.PacijentService;
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
	private PacijentService pacijentService;

	private KartonService kartonService;
	@Autowired
	private PacijentService patientService;
	@Autowired
	private MailService mailService;
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
	static class PregledSala{
		public PregledDTO pregled;
		public SalaDTO sala;
	}
	@PutMapping(value="/ispitaj",consumes = "application/json")
	public ResponseEntity<PregledDTO> ispitajPregled(@RequestBody PregledSala pregledSala) {
		SalaDTO sala=pregledSala.sala;
		PregledDTO pregled=pregledSala.pregled;
		System.out.println("POSLAT PREGLED:"+pregled.getDatum()+pregled.getLekar().getUsername());
		Pregled p = pregledService.findOne(pregled.getId());
		SalaPK kljuc=new SalaPK(sala.getBrojSale(),sala.getKlinika());
		Sala s=salaService.findOne(kljuc);
		p=pronadjiSlobodniTermin(p,s);
		PregledDTO pregledDTO=new PregledDTO(p);
		System.out.println("poslije poslatog PREGLED:"+pregledDTO.getDatum()+pregledDTO.getSala().getNaziv()+pregledDTO.getLekar().getUsername());
		return new ResponseEntity<PregledDTO>(pregledDTO, HttpStatus.OK);
	}
	static class PreglediSale implements Comparable<PreglediSale>{
		public Date pocetak;
		public Date kraj;
		public boolean slobodan;
		public PreglediSale(Date pocetak2, Date kraj2) {
			pocetak=pocetak2;
			kraj=kraj2;
		}
		@Override
		public int compareTo(PreglediSale o) {
			return pocetak.compareTo(o.pocetak);
		}
	}
	private Pregled pronadjiSlobodniTermin(Pregled p, Sala sala) {
		Lekar l=lekarService.findOne(p.getLekar().getId());
		boolean nasLekar=false,pronasao=false;
		Klinika k=klinikaService.findOne(sala.getKlinika().getId());
		Date pregledPrijePocetak=new Date(600000*60*24*365*100);
		Date pregledPrijeKraj=new Date(600000*60*24*365*100);
		Date pregledPoslijePocetak=new Date(0);
		Date pregledPoslijeKraj=new Date(0);
		Date pregledPocetak=p.getDatum();
		Date pregledKraj=p.getDatum();
		pregledKraj.setTime(pregledKraj.getTime()+ p.getTrajanje()*60000);
		List<Pregled> pregledi=sala.getPregledi();
		List<Operacija> operacije = sala.getOperacije();
		List<PreglediSale> sviPregledi=new ArrayList<PreglediSale>();
		sviPregledi.add(new PreglediSale(pregledPrijePocetak,pregledPrijeKraj));
		for(Pregled pr:pregledi) {
			Date pocetak=pr.getDatum();
			Date kraj =pr.getDatum();
			kraj.setTime(pocetak.getTime()+pr.getTrajanje()*600000);
			sviPregledi.add(new PreglediSale(pocetak,kraj));
		}
		for(Operacija pr:operacije) {
			Date pocetak=pr.getDatum();
			Date kraj =pr.getDatum();
			kraj.setTime(pocetak.getTime()+pr.getTrajanje()*600000);
			sviPregledi.add(new PreglediSale(pocetak,kraj));
		}
		Collections.sort(sviPregledi);
		//++++++++++++++++++++++++++++++++
		for(PreglediSale pr:sviPregledi) {
			pregledPrijePocetak=pregledPoslijePocetak;
			pregledPrijeKraj=pregledPoslijeKraj;
			pregledPoslijePocetak=pr.pocetak;
			pregledPoslijeKraj=pr.kraj;
			if((pregledPocetak.after(pregledPrijeKraj) && pregledKraj.before(pregledPoslijePocetak))) {
				//znaci period se nalazi izmedju 2 pregleda/operacije
				nasLekar=false;
				pronasao=false;
				for (Lekar lek: k.getLekari()) {
					if(l.getBrojOsiguranika()==lek.getBrojOsiguranika()) {
						nasLekar=true;//lekar koji je zatrazio pregled bi trebao  ga dobiti 
					}
					if( lek.getTipPregleda().getId()==p.getTip().getId()) {
						PreglediSale ps=JelSlobodanLekar(lek,pregledPocetak,pregledKraj,p,1);
						if(ps.slobodan==true) {
							p.setDatum(pregledPocetak);
							p.setSala(sala);
							p.setLekar(lek);
							pronasao=true;
						}
						if(nasLekar==true) {return p;}			
					}
				}
				if(pronasao==true) {return p;}
			}
		}
		pregledPoslijePocetak=new Date(0);
		pregledPoslijeKraj=new Date(0);
		for(PreglediSale pr:sviPregledi) {
			pregledPrijePocetak=pregledPoslijePocetak;
			pregledPrijeKraj=pregledPoslijeKraj;
			pregledPoslijePocetak=pr.pocetak;
			pregledPoslijeKraj=pr.kraj;
			if((pregledPocetak.before(pregledPrijeKraj) && (pregledPoslijePocetak.getTime()-pregledPrijeKraj.getTime()<=60000*p.getTrajanje()))){
				nasLekar=false;
				pronasao=false;
				for (Lekar lek: k.getLekari()) {
					if(l.getBrojOsiguranika()==lek.getBrojOsiguranika()) {
						nasLekar=true;
					}
					if( lek.getTipPregleda().getId()==p.getTip().getId()) {
						PreglediSale ps=JelSlobodanLekar(lek,pregledPocetak,pregledKraj,p,2);
						if(ps.slobodan==true) {
							p.setDatum(ps.pocetak);
							p.setSala(sala);
							p.setLekar(lek);
							pronasao=true;
						}
						if(nasLekar==true) {return p;}			
					}
				}
				if(pronasao==true) {return p;}
			}			
		}
		return p;
	}
	@PutMapping(value="/potvrdi",consumes = "application/json")
	public ResponseEntity<PregledDTO> updateCourse(@RequestBody PregledDTO preg) {
		System.out.println("ispitan za potvrdu PREGLED:"+preg.getDatum()+preg.getSala().getNaziv()+preg.getLekar().getUsername());
		Pregled p = pregledService.findOne(preg.getId());
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		p.setDatum(preg.getDatum());
		SalaPK kljuc=new SalaPK(preg.getSala().getBrojSale(),preg.getSala().getKlinika());
		Sala s=salaService.findOne(kljuc);
		p.setSala(s);
		Lekar l=lekarService.findOne(preg.getLekar().getId());
		p.setLekar(l);
		
		String mail=l.getEmail();
		String naslov="Zakazivanje pregleda";
		String tekst="Poštovani,"
				+ "\nVas pregled je potvrdjen za  "+p.getDatum().toString().substring(0,16)
		        + ".\nPregled ce se odrzati u sali "+p.getSala().getNaziv()+",broj:"+p.getSala().getBrojSale()+".";
		mailService.SendMail(mail, naslov, tekst);
		Pacijent pacijent=patientService.findOneByKarton(p.getKarton());
		mail=pacijent.getEmail();
		mailService.SendMail(mail, naslov, tekst);
		pregledService.save(p);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	public Pregled algoritamPregled(Pregled pregled) {
		
		Pregled najboljiPregled=pregled;
		Klinika k=klinikaService.findOne(pregled.getLekar().getKlinika().getId());
		List<Sala> sale=salaService.findAll(k);
		for (Sala sala : sale) {
			Pregled p=pronadjiSlobodniTermin(pregled,sala);
			if (p.getDatum().before(najboljiPregled.getDatum())){
				najboljiPregled=p;
			}
		}
		return najboljiPregled;		
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
		if(s==null) {
			Klinika klinika=l.getKlinika();
			AdministratorKlinike ak=akService.findByKlinika(klinika);
			String mail=ak.getEmail();
			String naslov="Zakazivanje pregleda";
			String tekst="Poštovani,"
					+ "\nNovi pregled zakazan je za  "+pregled.getDatum().toString().substring(0,16)
			        + "\nMolimo vas da rezervisete salu u toku dana.";
			mailService.SendMail(mail, naslov, tekst);
		}
		pregledService.save(pregled);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@GetMapping
	(value = "/posalji")
	public ResponseEntity<String> posaljiZahtev(@RequestParam String datum,@RequestParam String tip,@RequestParam String klinika,@RequestParam String lekar,@RequestParam String pacijent) throws ParseException {
		System.out.println(datum + tip + klinika + lekar + pacijent);
		Lekar l = lekarService.findByEmail(lekar);
		klinika = klinika.replace(':', ' ');
		System.out.println(datum + tip + klinika + lekar + pacijent);
		Klinika k = klinikaService.findByNaziv(klinika);
		Pacijent pa = pacijentService.findByEmail(pacijent);
		if(pa.getKarton() == null) {
			return new ResponseEntity<>("Nemate kreiran zdtavstveni karton!",HttpStatus.BAD_REQUEST);
		}
		if(l == null) {
			return new ResponseEntity<>("Ne postoji lekar!",HttpStatus.BAD_REQUEST);
		}
		if(k == null) {
			return new ResponseEntity<>("Ne postoji klinika!",HttpStatus.BAD_REQUEST);
		}
		if(l.getTipPregleda() == null) {
			return new ResponseEntity<>("Lekar nije specijalizovan za dati tip pregleda!",HttpStatus.BAD_REQUEST);
		}
		if(!l.getTipPregleda().getNaziv().equals(tip)) {
			return new ResponseEntity<>("Lekar nije specijalizovan za dati tip pregleda!",HttpStatus.BAD_REQUEST);
		}
		if(!l.getKlinika().getNaziv().equals(klinika)) {
			return new ResponseEntity<>("Odabrani lekar nije zaposlen u klinici!",HttpStatus.BAD_REQUEST);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date vremePregleda = sdf.parse(datum);
		List<Pregled> svipregledi = pregledService.findAll();
		for (Pregled p: svipregledi) {
			if (p.getLekar().getEmail().equals(l.getEmail())) {
				//pregled za tog lekara
				Calendar doKada = Calendar.getInstance(); // creates calendar
				doKada.setTime(p.getDatum()); // sets calendar time/date
				doKada.add(Calendar.MINUTE, p.getTrajanje()); // adds one hour
				
				if (vremePregleda.after(p.getDatum()) && vremePregleda.before(doKada.getTime())){
					return new ResponseEntity<>("Odabrani lekar je zauzet!",HttpStatus.BAD_REQUEST);

				}
				
			}
		}
		Set<GodisnjiOdmor> godisnji = l.getGodisnji();
		for(GodisnjiOdmor go : godisnji) {
			if (go.getDatumPocetka().before(vremePregleda) && go.getDatumKraja().after(vremePregleda)) {
				return new ResponseEntity<>("Odabrani lekar je na godisnjem!",HttpStatus.BAD_REQUEST);

			}
		}
		Pregled p = new Pregled();
		p.setDatum(vremePregleda);
		p.setKarton(pa.getKarton());
		p.setLekar(l);
		p.setTip(l.getTipPregleda());
		//pregledService.save(p);
		return new ResponseEntity<>("Ok!",HttpStatus.OK);}
		
		
	private PreglediSale JelSlobodanLekar(Lekar l, Date pPocetak, Date pPoslije,Pregled pregledTaj,int nacin) {
		PreglediSale ps=new PreglediSale(new Date(),new Date());
		List<PreglediSale> sviPregledi=new ArrayList<PreglediSale>();
		Date pocetak=new Date(0);
		Date kraj =new Date(0);
		sviPregledi.add(new PreglediSale(pocetak,kraj));
		pocetak=new Date(600000*60*24*365*100);
		kraj =new Date(600000*60*24*365*100);
		sviPregledi.add(new PreglediSale(pocetak,kraj));
		for(Pregled pr:l.getPregledi()) {
			pocetak=pr.getDatum();
			kraj =pr.getDatum();
			kraj.setTime(pocetak.getTime()+pr.getTrajanje()*600000);
			sviPregledi.add(new PreglediSale(pocetak,kraj));
		}
		for(Operacija pr:l.getOperacije()) {
			pocetak=pr.getDatum();
			kraj =pr.getDatum();
			kraj.setTime(pocetak.getTime()+pr.getTrajanje()*600000);
			sviPregledi.add(new PreglediSale(pocetak,kraj));
		}
		Collections.sort(sviPregledi);
		if(nacin==1) {
			boolean mozel=true;
			for (Pregled pr : l.getPregledi()) {
				Date prPoslije=pr.getDatum();
				prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
				if((pPocetak.after(pr.getDatum()) && pPocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
					if(pregledTaj.getId()!=pr.getId()) {
						ps.slobodan=false;
						return ps;
					}
				}
			}
			if(mozel==true) {
				for (Operacija pr : l.getOperacije()) {
					Date prPoslije=pr.getDatum();
					prPoslije.setTime(prPoslije.getTime()+ pr.getTrajanje()*60000);
					if((pPocetak.after(pr.getDatum()) && pPocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije))) {
						ps.slobodan=false;
						return ps;
					}
				}	
			}
			ps.slobodan=true;
			return ps;
		}else {
			for (PreglediSale pr : sviPregledi) {
				PreglediSale presjek=pronadjiPresjek(pPocetak,pPoslije,pr.pocetak,pr.kraj);
				if(presjek.kraj.getTime()-presjek.pocetak.getTime()>=60000*pregledTaj.getTrajanje()){
					ps.pocetak=presjek.pocetak;
					ps.slobodan=true;
					return ps;
				}
			}
		}
		ps.slobodan=false;
		return ps;
	}
	private PreglediSale pronadjiPresjek(Date pPocetak, Date pPoslije, Date pocetak, Date kraj) {
		PreglediSale presjek= null;
		if(pocetak.compareTo(pPoslije)>0 || kraj.compareTo(pPocetak)<0) {
			presjek.pocetak=new Date();
			presjek.kraj=presjek.pocetak;
		}else {
			presjek.pocetak.setTime(Math.max(pPocetak.getTime(),pocetak.getTime()));
			presjek.kraj.setTime(Math.max(pPoslije.getTime(),kraj.getTime()));
		}
		return presjek;
	}
}
