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
import rest.domain.Uloga;
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
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
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
		Klinika k=klinikaService.findOne(ak.getKlinika().getId());
		List<Pregled> sltermini = pregledService.findZakazane();
		System.out.println(sltermini.isEmpty());
		List<PregledDTO> slterminiDTO = new ArrayList<>();
		for (Pregled s : sltermini) {
			System.out.println("kyle");
			Lekar l=lekarService.findOne(s.getLekar().getId());
			if(l.getKlinika().getId()==k.getId()) {
				slterminiDTO.add(new PregledDTO(s));
			}
			
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
	public ResponseEntity<PregledDTO> ispitajPregled(@RequestBody PregledSala pregledSala) throws ParseException {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		SalaDTO sala=pregledSala.sala;
		PregledDTO pregled=pregledSala.pregled;
		//System.out.println("((((((((((((((((((((((((((((((((((((((((((((((");
		//System.out.println("POSLAT PREGLED:"+pregled.getDatum()+pregled.getLekar().getUsername()+sala.getNaziv()+sala.getBrojSale());
		//System.out.println("((((((((((((((((((((((((((((((((((((((((((((((");
		Pregled p = pregledService.findOne(pregled.getId());
		SalaPK kljuc=new SalaPK(sala.getBrojSale(),sala.getKlinika());
		Sala s=salaService.findOne(kljuc);
		System.out.println(p.getId()+" "+s.getId());
		System.out.println("=================================================================");
		p=pronadjiSlobodniTermin(p,s);
		System.out.println("=================================================================");
		PregledDTO pregledDTO=new PregledDTO(p);
		//System.out.println("poslije poslatog PREGLED:"+pregledDTO.getDatum()+pregledDTO.getSala().getNaziv()+pregledDTO.getLekar().getUsername());
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
	private Pregled pronadjiSlobodniTermin(Pregled p, Sala sala) throws ParseException {
		Lekar l=lekarService.findOne(p.getLekar().getId());
		boolean nasLekar=false,pronasao=false;
		Klinika k=klinikaService.findOne(sala.getKlinika().getId());
		String sDate1="31/12/2030";  
		Date pregledPrijePocetak=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);	
		Date pregledPrijeKraj=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		Date pregledPoslijePocetak=new Date(0);
		Date pregledPoslijeKraj=new Date(0);
		Date pregledPocetak=p.getDatum();
		Date pregledKraj=new Date();
		pregledKraj.setTime(pregledPocetak.getTime()+ p.getTrajanje()*60000);
		List<Pregled> pregledi=sala.getPregledi();
		List<Operacija> operacije = sala.getOperacije();
		List<PreglediSale> sviPregledi=new ArrayList<PreglediSale>();
		sviPregledi.add(new PreglediSale(pregledPrijePocetak,pregledPrijeKraj));
		for(Pregled pr:pregledi) {
			Date pocetak=pr.getDatum();
			Date kraj =new Date();
			kraj.setTime(pocetak.getTime()+pr.getTrajanje()*60000);
			sviPregledi.add(new PreglediSale(pocetak,kraj));
		}
		for(Operacija pr:operacije) {
			Date pocetak=pr.getDatum();
			Date kraj =new Date();
			kraj.setTime(pocetak.getTime()+pr.getTrajanje()*60000);
			sviPregledi.add(new PreglediSale(pocetak,kraj));
		}
		Collections.sort(sviPregledi);
		//++++++++++++++++++++++++++++++++
		for(PreglediSale pr:sviPregledi) {
			pregledPrijePocetak =new Date();
			pregledPrijePocetak.setTime(pregledPoslijePocetak.getTime());
			pregledPrijeKraj =new Date();
			pregledPrijeKraj.setTime(pregledPoslijeKraj.getTime());
			pregledPoslijePocetak =new Date();
			pregledPoslijePocetak.setTime(pr.pocetak.getTime());
			pregledPoslijeKraj =new Date();
			pregledPoslijeKraj.setTime(pr.kraj.getTime());
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
		System.out.println("pregledPrijePocetak: "+pregledPrijePocetak.toString());
		System.out.println("pregledPrijeKraj: "+pregledPrijeKraj.toString());
		System.out.println("pregledPoslijePocetak: "+pregledPoslijePocetak.toString());
		System.out.println("pregledPoslijeKraj: "+pregledPoslijeKraj.toString());
		System.out.println("pregledPocetak: "+pregledPocetak.toString());
		System.out.println("pregledPocetak: "+pregledKraj.toString());
		for(PreglediSale pr:sviPregledi) {
			pregledPrijePocetak =new Date();
			pregledPrijePocetak.setTime(pregledPoslijePocetak.getTime());
			pregledPrijeKraj =new Date();
			pregledPrijeKraj.setTime(pregledPoslijeKraj.getTime());
			pregledPoslijePocetak =new Date();
			pregledPoslijePocetak.setTime(pr.pocetak.getTime());
			pregledPoslijeKraj =new Date();
			pregledPoslijeKraj.setTime(pr.kraj.getTime());
			if((pregledPocetak.before(pregledPrijeKraj) && ((pregledPoslijePocetak.getTime()-pregledPrijeKraj.getTime())>=60000*p.getTrajanje()))){
				nasLekar=false;
				pronasao=false;
				for (Lekar lek: k.getLekari()) {
					if(l.getBrojOsiguranika()==lek.getBrojOsiguranika()) {
						nasLekar=true;
					}
					if( lek.getTipPregleda().getId()==p.getTip().getId()) {
						PreglediSale ps=JelSlobodanLekar(lek,pregledPrijeKraj,pregledPoslijePocetak,p,2);
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
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		/*
		String mail=l.getEmail();
		String naslov="Zakazivanje pregleda";
		String tekst="Poštovani,"
				+ "\nVas pregled je potvrdjen za  "+p.getDatum().toString().substring(0,16)
		        + ".\nPregled ce se odrzati u sali "+p.getSala().getNaziv()+",broj:"+p.getSala().getBrojSale()+".";
		mailService.SendMail(mail, naslov, tekst);
		Pacijent pacijent=patientService.findOneByKarton(p.getKarton());
		mail=pacijent.getEmail();
		mailService.SendMail(mail, naslov, tekst);
		*/
		pregledService.save(p);
		return new ResponseEntity<PregledDTO>(new PregledDTO(p), HttpStatus.OK);
	}
	public Pregled algoritamPregled(Pregled pregled) throws ParseException {
		
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
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE && tipKorisnika()!=Uloga.LEKAR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		if(tipKorisnika()!=Uloga.PACIJENT ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		
		
	private PreglediSale JelSlobodanLekar(Lekar l, Date pPocetak, Date pPoslije,Pregled pregledTaj,int nacin) throws ParseException {
		System.out.println("JEL SLOBODAN LEKAR"+l.getEmail()+pPocetak.toString()+pPoslije.toString()+pregledTaj.getId()+" nacin:"+nacin);
		PreglediSale ps=new PreglediSale(new Date(),new Date());
		List<PreglediSale> sviPregledi=new ArrayList<PreglediSale>();
		Date pocetak=new Date(0);
		Date kraj =new Date(0);
		PreglediSale piS=new PreglediSale(pocetak,kraj);
		sviPregledi.add(piS);
		String sDate1="31/12/2030";  
		Date staripocetak=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		Date starikraj =new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		sviPregledi.add(new PreglediSale(staripocetak,starikraj));
		for(Pregled pr:l.getPregledi()) {
			System.out.println(pr.getId()+" xyle");
			Date npocetak=pr.getDatum();
			Date nkraj =new Date();
			nkraj.setTime(npocetak.getTime()+pr.getTrajanje()*60000);
			sviPregledi.add(new PreglediSale(npocetak,nkraj));
		}
		for(Operacija pr:l.getOperacije()) {
			Date npocetak=pr.getDatum();
			Date nkraj =new Date();
			nkraj.setTime(npocetak.getTime()+pr.getTrajanje()*60000);
			sviPregledi.add(new PreglediSale(npocetak,nkraj));
		}
		Collections.sort(sviPregledi);
		if(nacin==1) {
			boolean mozel=true;
			for (Pregled pr : l.getPregledi()) {
				Date prPoslije=new Date();
				prPoslije.setTime(pr.getDatum().getTime()+ pr.getTrajanje()*60000);
				if((pPocetak.after(pr.getDatum()) && pPocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije)) ||
				(pPocetak.before(pr.getDatum()) && pPocetak.before(prPoslije) && 
				pPoslije.after(pr.getDatum()) && pPoslije.after(prPoslije)) || (pPocetak.equals(pr.getDatum()))) {
					if(pregledTaj.getId()!=pr.getId()) {
						System.out.println("SISO3");
						ps.slobodan=false;
						return ps;
					}
				}
			}
			if(mozel==true) {
				for (Operacija pr : l.getOperacije()) {
					Date prPoslije=new Date();
					prPoslije.setTime(pr.getDatum().getTime()+ pr.getTrajanje()*60000);
					if((pPocetak.after(pr.getDatum()) && pPocetak.before(prPoslije))|| (pPoslije.after(pr.getDatum()) && pPoslije.before(prPoslije)) ||
					(pPocetak.before(pr.getDatum()) && pPocetak.before(prPoslije) && 
					pPoslije.after(pr.getDatum()) && pPoslije.after(prPoslije)) ||(pPocetak.equals(pr.getDatum()))) {
						System.out.println("SISO2");
						ps.slobodan=false;
						return ps;
					}
				}	
			}
			ps.slobodan=true;
			System.out.println("SISO4");
			return ps;
		}else {
			Date proslikraj=null;
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
			for(PreglediSale apr:sviPregledi) {
				System.out.println("Pocetak: "+apr.pocetak.toString()+",Kraj:"+apr.kraj.toString());
			}
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
			for (PreglediSale pr : sviPregledi) {
				if(proslikraj==null) {
					proslikraj=new Date();
					proslikraj.setTime(pr.kraj.getTime());
				}
				PreglediSale presjek=pronadjiPresjek(pPocetak,pPoslije,proslikraj,pr.pocetak);
				System.out.println("presjek pocetak: "+presjek.pocetak.toString());
				System.out.println("presjek kraj: "+presjek.kraj.toString());
				System.out.println(((presjek.kraj.getTime()-presjek.pocetak.getTime())-60000*pregledTaj.getTrajanje()));
				if(presjek.kraj.getTime()-presjek.pocetak.getTime()>=60000*pregledTaj.getTrajanje()){
					System.out.println("SISO");
					ps.pocetak=new Date();
					ps.pocetak.setTime(presjek.pocetak.getTime());
					ps.slobodan=true;
					return ps;
				}
			}
		}
		ps.slobodan=false;
		System.out.println("SISO5");
		return ps;
	}
	private PreglediSale pronadjiPresjek(Date pPocetak, Date pPoslije, Date pocetak, Date kraj) {
		PreglediSale presjek=new PreglediSale(new Date(), new Date());
		if(pocetak.compareTo(pPoslije)>0 || kraj.compareTo(pPocetak)<0) {
			presjek.pocetak=new Date();
			presjek.kraj=new Date();
		}else {
			presjek.pocetak.setTime(Math.max(pPocetak.getTime(),pocetak.getTime()));
			presjek.kraj.setTime(Math.max(pPoslije.getTime(),kraj.getTime()));
		}
		return presjek;
	}
}
