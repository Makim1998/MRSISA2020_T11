package rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
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

import rest.controller.PregledController.PregledSala;
import rest.controller.PregledController.PreglediSale;
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
import rest.dto.OperacijaDTO;
import rest.dto.PregledDTO;
import rest.dto.SalaDTO;
import rest.pk.SalaPK;
import rest.service.AdminKService;
import rest.service.KartonService;
import rest.service.KlinikaService;
import rest.service.LekariService;
import rest.service.MailService;
import rest.service.OperacijaService;
import rest.service.PacijentService;
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
	private PacijentService pacijentService;
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
	}*/
	@GetMapping
	(value = "/zavrseni/{id}")
	public ResponseEntity<List<OperacijaDTO>> getZavrseniTerminiPregleda(@PathVariable Integer id) throws ParseException {
		List<Operacija> sltermini = operacijaService.findZavrsene();
		System.out.println(sltermini.isEmpty());
		List<OperacijaDTO> slterminiDTO = new ArrayList<OperacijaDTO>();
		for (Operacija s : sltermini) {
			for (Lekar l: s.getLekari() ) {
				if(l.getKlinika().getId()==id) {
					slterminiDTO.add(new OperacijaDTO(s));
					break;
				}
			}
		}
		Collections.sort(slterminiDTO);
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	
	@GetMapping
	(value = "/zahtevi/{id}")
	public ResponseEntity<List<OperacijaDTO>> getZahteviregleda(@PathVariable Integer id) throws ParseException {
		System.out.println("kiko1");
		AdministratorKlinike ak=akService.findOne(id);
		Klinika k=klinikaService.findOne(ak.getKlinika().getId());
		List<Operacija> sltermini = operacijaService.findZakazane();
		System.out.println(sltermini.isEmpty());
		List<OperacijaDTO> slterminiDTO = new ArrayList<>();
		for (Operacija s : sltermini) {
			System.out.println("mila moja");
			Lekar l=lekarService.findOne(s.getLekari().iterator().next().getId());
			if(l.getKlinika().getId()==k.getId()) {
				slterminiDTO.add(new OperacijaDTO(s));
			}
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	@GetMapping
	(value = "/nezahtevi/{id}")
	public ResponseEntity<List<OperacijaDTO>> getNeZahteviregleda(@PathVariable Integer id) throws ParseException {
		System.out.println("kiko1");
		AdministratorKlinike ak=akService.findOne(id);
		Klinika k=klinikaService.findOne(ak.getKlinika().getId());
		List<Operacija> sltermini = operacijaService.findNeZakazane();
		System.out.println(sltermini.isEmpty());
		List<OperacijaDTO> slterminiDTO = new ArrayList<>();
		for (Operacija s : sltermini) {
			System.out.println("kyle");
			Lekar l=lekarService.findOne(s.getLekari().iterator().next().getId());
			if(l.getKlinika().getId()==k.getId()) {
				slterminiDTO.add(new OperacijaDTO(s));
			}
		}
		System.out.println("kiko3");
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}
	/*
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
	}*/
	static class LekariOperacija{
		public List<LekarDTO> lekari;
		public OperacijaDTO operacija;
	}
	
	@PutMapping(value="/potvrdi",consumes = "application/json")
	public ResponseEntity<OperacijaDTO> updateCourse(@RequestBody LekariOperacija lekariOperacija) {

		// a course must exist
		OperacijaDTO operacijaDTO = lekariOperacija.operacija;
		List<LekarDTO> lekariDTO = lekariOperacija.lekari;
		Operacija p = operacijaService.findOne(operacijaDTO.getId());

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//System.out.println(operacijaDTO.getSala().getKlinika());
		Klinika k= klinikaService.findOne(operacijaDTO.getSala().getKlinika());
		Sala s=new Sala(k,operacijaDTO.getSala().getBrojSale(),operacijaDTO.getSala().getNaziv());
		p.setSala(s);
		p.setDatum(operacijaDTO.getDatum());
		HashSet<Lekar> lekari = new HashSet<Lekar>();
		for (LekarDTO l: lekariDTO) {
			System.out.println("lekar za operaciju: " + l.getIme() + " " + l.getPrezime());
			Lekar lekar = lekarService.findOne(l.getId());
			lekari.add(lekar);
		}
		p.setLekari(lekari);
		String naslov="Zakazivanje operacije";
		String tekst="Poštovani,"
				+ "\nVas operacija je potvrdjena za  "+p.getDatum().toString().substring(0,16)
		        + ".\nOperacija ce se odrzati u sali "+p.getSala().getNaziv()+",broj:"+p.getSala().getBrojSale()+".";
		Pacijent pacijent = pacijentService.findOneByKarton(p.getKarton());
		String email = pacijent.getEmail();
		mailService.SendMail(email, naslov, tekst);
		for (Lekar l: p.getLekari()) {
			email = l.getEmail();
			tekst="Poštovani,"
					+ "\nOperacija Vam je potvrdjena za  "+p.getDatum().toString().substring(0,16)
			        + ".\nOperacija ce se odrzati u sali "+p.getSala().getNaziv()+",broj:"+p.getSala().getBrojSale()+"."
			        + "\nU obavezi ste da prisustvujete.";
			mailService.SendMail(email, naslov, tekst);
		}
		System.out.println("15. MAJ");
		operacijaService.save(p);
		System.out.println("saa");
		return new ResponseEntity<OperacijaDTO>(new OperacijaDTO(p), HttpStatus.OK);
	}
	
	/*
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
	static class PregledSala{
		public OperacijaDTO pregled;
		public SalaDTO sala;
	}
	@PutMapping(value="/ispitaj",consumes = "application/json")
	public ResponseEntity<OperacijaDTO> ispitajPregled(@RequestBody PregledSala pregledSala) throws ParseException {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		SalaDTO sala=pregledSala.sala;
		OperacijaDTO pregled=pregledSala.pregled;
		//System.out.println("((((((((((((((((((((((((((((((((((((((((((((((");
		//System.out.println("POSLAT PREGLED:"+pregled.getDatum()+pregled.getLekar().getUsername()+sala.getNaziv()+sala.getBrojSale());
		//System.out.println("((((((((((((((((((((((((((((((((((((((((((((((");
		Operacija p = operacijaService.findOne(pregled.getId());
		SalaPK kljuc=new SalaPK(sala.getBrojSale(),sala.getKlinika());
		Sala s=salaService.findOne(kljuc);
		System.out.println(p.getId()+" "+s.getId());
		System.out.println("=================================================================");
		p=pronadjiSlobodniTermin(p,s);
		System.out.println("=================================================================");
		OperacijaDTO pregledDTO=new OperacijaDTO(p);
		//System.out.println("poslije poslatog PREGLED:"+pregledDTO.getDatum()+pregledDTO.getSala().getNaziv()+pregledDTO.getLekar().getUsername());
		return new ResponseEntity<OperacijaDTO>(pregledDTO, HttpStatus.OK);
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
	private Operacija pronadjiSlobodniTermin(Operacija p, Sala sala) throws ParseException {
		Lekar l=p.getLekari().iterator().next();
		boolean nasLekar=false,pronasao=false;
		Lekar potreban=l;
		HashSet<Lekar> lekari=new HashSet<Lekar>();
		p.getLekari().clear();
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
					PreglediSale ps=JelSlobodanLekar(lek,pregledPocetak,pregledKraj,p,1);
					if(ps.slobodan==true) {
						p.setDatum(pregledPocetak);
						p.setSala(sala);
						potreban=lek;
						pronasao=true;
					}
					if(nasLekar==true) {
						lekari.add(potreban);
						p.setLekari(lekari);
						return p;
					}			
				}
				if(pronasao==true) {
					lekari.add(potreban);
					p.setLekari(lekari);
					return p;
				}
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
					PreglediSale ps=JelSlobodanLekar(lek,pregledPrijeKraj,pregledPoslijePocetak,p,2);
					if(ps.slobodan==true) {
						p.setDatum(ps.pocetak);
						p.setSala(sala);
						potreban=lek;
						pronasao=true;
					}
					if(nasLekar==true) {
						lekari.add(potreban);
						p.setLekari(lekari);
						return p;
					}			
				}
				if(pronasao==true) {
					lekari.add(potreban);
					p.setLekari(lekari);
					return p;
				}
			}			
		}
		lekari.add(potreban);
		p.setLekari(lekari);
		return p;
	}
	private PreglediSale JelSlobodanLekar(Lekar l, Date pPocetak, Date pPoslije,Operacija pregledTaj,int nacin) throws ParseException {
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
	
	static class OdlaganjeO{
		public Integer id;
		public Date novi;
	}
	
	@PutMapping(value="/odlozi")
	public ResponseEntity<Void> odloziOp(@RequestBody OdlaganjeO odlaganje) throws ParseException{
		Integer id = odlaganje.id;
		if (tipKorisnika() != Uloga.ADMINISTRATOR_KLINIKE)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Date datum = odlaganje.novi;
		Operacija operacija = operacijaService.findOne(id);
		System.out.println("Izmenjeni datum parsiran: " + datum);
		System.out.println("Trenutni datum operacije: " + operacija.getDatum());
		if (datum.before(operacija.getDatum())) {
			System.out.println("Novi datum nije posle starog");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Date krajOperacije = new Date(datum.getTime());
		krajOperacije.setTime(krajOperacije.getTime() + 60000*operacija.getTrajanje());
		for (Operacija o: operacijaService.findNeZakazane()) {
			if (operacija.getId() != o.getId()) {
				Date temp_kraj = new Date(o.getDatum().getTime());
				temp_kraj.setTime(temp_kraj.getTime() + 60000*o.getTrajanje());
				if (o.getDatum().before(datum) && temp_kraj.after(datum)) {
					if (o.getSala().getId() == operacija.getSala().getId()) {
						System.out.println("Preklapanje sa salom (pocinje pre neke operacije)");
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				}
				if (o.getDatum().after(datum) && o.getDatum().before(krajOperacije)) {
					if (o.getSala().getId() == operacija.getSala().getId()) {
						System.out.println("Preklapanje sa salom (pocinje posle neke operacije)");
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY HH:mm");
		String stariDatum = sdf.format(operacija.getDatum());
		String noviDatum = sdf.format(datum);
		String naslov="Odlaganje operacije";
		String tekst="Poštovani,"
				+ "\nVas operacija rezervisana za "+stariDatum
		        + " je odlozena na "+noviDatum;
		Pacijent pacijent = pacijentService.findOneByKarton(operacija.getKarton());
		String email = pacijent.getEmail();
		mailService.SendMail(email, naslov, tekst);
		tekst += "\nU obavezi ste da prisustvujete.";
		for (Lekar l: operacija.getLekari()) {
			email = l.getEmail();
			mailService.SendMail(email, naslov, tekst);
		}
		operacija.setDatum(datum);
		operacija = operacijaService.save(operacija);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

