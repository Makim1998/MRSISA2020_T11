package rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import rest.domain.AdministratorKlinickogCentra;
import rest.domain.GodisnjiOdmor;
import rest.domain.KlinickiCentar;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.Pregled;
import rest.domain.StavkaCenovnika;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.KlinikaDTO;
import rest.dto.LekarDTO;
import rest.service.AdminKCService;
import rest.service.KlinickiCentarService;
import rest.service.KlinikaService;
import rest.service.PregledService;
import rest.service.StavkaCenovnikaService;


@RestController
@RequestMapping("rest/klinika")
public class KlinikaController {
	
	@Autowired
	private KlinikaService service;
	
	@Autowired
	private PregledService pregledi;
	
	@Autowired
	private StavkaCenovnikaService stavke;
	
	@Autowired
	public HttpServletRequest request;
	
	@Autowired
	private AdminKCService adminService;
	
	@Autowired
	private KlinickiCentarService kcService;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
	private AdministratorKlinickogCentra getAdmin() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return adminService.findOne(logedIn.getId());
	}

	
	@GetMapping
	public ResponseEntity<List<KlinikaDTO>> getKlinike(){
		List<Klinika> klinike = service.findAll();
		List<KlinikaDTO> ret = new ArrayList<KlinikaDTO>();
		AdministratorKlinickogCentra admin = getAdmin();
		for (Klinika k: klinike) {
			KlinikaDTO dto = new KlinikaDTO(k);
			dto.setProsek(k);
			if (admin.getKlinickiCentar().getId() == k.getKlinickiCentar().getId())
				ret.add(dto);
		}
		for (KlinickiCentar kc: kcService.findAll()) {
			for (Klinika k: kc.getKlinike()) {
				System.out.println(k.getId() + " " + k.getNaziv() + " " + k.getAdresa());
			}
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<KlinikaDTO> updateCourse(@RequestBody KlinikaDTO kDTO) {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE && tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("IDEMO1");
		Klinika k = service.findOne(kDTO.getId());

		if (k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		k.setNaziv(kDTO.getNaziv());
		k.setAdresa(kDTO.getAdresa());
		k.setOpis(kDTO.getOpis());
		k= service.save(k);
		return new ResponseEntity<>(new KlinikaDTO(k), HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> dodajKlinika(@RequestBody KlinikaDTO klinikaDto){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("naziv: " + klinikaDto.getNaziv());
		System.out.println("adresa: " + klinikaDto.getAdresa());
		System.out.println("opis: " + klinikaDto.getOpis());
		Klinika klinika = new Klinika(klinikaDto);
		System.out.println("Kreirana je nova klinika");
		User user = (User) request.getSession().getAttribute("korisnik");
		AdministratorKlinickogCentra administrator = adminService.findOne(user.getId());
		KlinickiCentar kc = kcService.findOne(administrator.getKlinickiCentar().getId());
		klinika.setKlinickiCentar(kc);
		System.out.println("Dodeljen je odgovarajuci klinicki centar");
		klinika = service.save(klinika);
		System.out.println("Dodata je nova klinika");
		System.out.println(klinika.getKlinickiCentar().getNaziv());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> obrisiKlinika(@PathVariable Integer id){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Klinika klinika = service.findOne(id);
		System.out.println("Stigne do backend-a za brisanje klinike - id: "+id);
		if (klinika.getCenovnik() != null) {
			System.out.println("Problem je kod cenovnika");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (klinika.getAdministrator() != null) {
			System.out.println("Problem je kod administratora");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (klinika.getMedicinskeSestre().size() > 0) {
			System.out.println("Problem je kod med sestri");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (klinika.getSale().size() > 0) {
			System.out.println("Problem je kod sala");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		KlinickiCentar kc = kcService.findOne(klinika.getKlinickiCentar().getId());
		Set<Klinika> izmena = kc.getKlinike();
		izmena.remove(klinika);
		kc.setKlinike(izmena);
		kc = kcService.save(kc);
		service.remove(id);
		for (Klinika k: service.findAll()) {
			System.out.println(k.getId() + " " + k.getNaziv());
		}
		System.out.println("Trebalo bi da je klinika obrisana");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/adminIds")
	public ResponseEntity<List<Integer>> getAdminss(){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Klinika> klinike = service.findAll();
		List<Integer> ids = new ArrayList<Integer>();
		for (Klinika k: klinike) {
			if (k.getAdministrator() == null)
				ids.add(k.getId());
		}
		return new ResponseEntity<>(ids, HttpStatus.OK);
	}
	
	@GetMapping(value="/cenovnikIds")
	public ResponseEntity<List<Integer>> getCenovnici(){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Klinika> klinike = service.findAll();
		List<Integer> ids = new ArrayList<Integer>();
		for (Klinika k: klinike) {
			if (k.getCenovnik() == null)
				ids.add(k.getId());
		}
		return new ResponseEntity<>(ids, HttpStatus.OK);
	}
	
	@PostMapping(value="/ocena",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KlinikaDTO> ocene(@RequestParam String klinika,@RequestParam String ocena,@RequestParam String pacijent){
		if(tipKorisnika()!=Uloga.PACIJENT) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Klinika k = service.findByNaziv(klinika);
		System.out.println("Ocenjivanje klinike");
		System.out.println(ocena);
		System.out.println(klinika);
		if(k == null) {
			System.out.println("Ne postoji klinika");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(ocena.equals("")) {
			System.out.println("Ocena nevalidna");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Pregled> svi = pregledi.findAll();
		boolean moze = false;
		for(Pregled p : svi) {
			if (p.getLekar().getKlinika().getNaziv().equals(klinika)) {
				if(p.getKarton() != null) {
					if(p.getKarton().getPacijent().getEmail().equals(pacijent)) {
						moze = true;
					}
				}
			}
		}
		if(!moze) {
			KlinikaDTO ki= new KlinikaDTO();
			ki.setNaziv("Ne mozete oceniti kliniku u kojoj niste imali pregled!");
			return new  ResponseEntity<>(ki,HttpStatus.BAD_REQUEST);
		}
		k.getOcene().add(Integer.parseInt(ocena));
		service.save(k);
		System.out.println("Ocena uspesno dodata");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/pretraga",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaDTO>> pretragaKlinike(@RequestParam String datum,@RequestParam String tip,@RequestParam String naziv,@RequestParam String adresa,@RequestParam String prosek) throws ParseException{
		List<Klinika> klinike = service.findAll();
		System.out.println(datum+tip+adresa+naziv+prosek);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date vremePregleda = sdf.parse(datum);
		List<KlinikaDTO> pronadjene = new ArrayList<KlinikaDTO>();
		for (Klinika k: klinike) {
			boolean zadovoljenaKlinika = false;
			Set<Lekar> lekari = k.getLekari();
			ArrayList<LekarDTO> pronadjeniLekari = new ArrayList<LekarDTO>();
			for (Lekar l : lekari) {
				boolean zadovoljenLekar = true;
				if (l.getTipPregleda().getNaziv().equals(tip)) {
					// proveri da li specijalizovan lekar ide na godisnji za datum
					//proveri da li ima zakazan vec drugi pregled za datum
					List<Pregled> svipregledi = pregledi.findAll();
					for (Pregled p: svipregledi) {
						if (p.getLekar().getEmail().equals(l.getEmail())) {
							//pregled za tog lekara
							Calendar doKada = Calendar.getInstance(); // creates calendar
							doKada.setTime(p.getDatum()); // sets calendar time/date
							doKada.add(Calendar.MINUTE, p.getTrajanje()); // adds one hour
							
							if (vremePregleda.after(p.getDatum()) && vremePregleda.before(doKada.getTime())){
								zadovoljenLekar = false;
							}
							
						}
					}
					Set<GodisnjiOdmor> godisnji = l.getGodisnji();
					for(GodisnjiOdmor go : godisnji) {
						if (go.getDatumPocetka().before(vremePregleda) && go.getDatumKraja().after(vremePregleda)) {
							zadovoljenLekar = false;
						}
					}
					if (zadovoljenLekar) {
						zadovoljenaKlinika = true;
						LekarDTO pronadjen = new LekarDTO(l);
						pronadjen.setProsek(l);
						pronadjeniLekari.add(pronadjen);
					}
				}
				
			}
			if(zadovoljenaKlinika) {
				System.out.println(k.getNaziv());
				KlinikaDTO dto = new KlinikaDTO(k);
				List<StavkaCenovnika> sce = stavke.findAll();
				int cena = 0;
				for(StavkaCenovnika sc: sce ) {
					if(sc.getUsluga().equals(tip + " pregled")) {
						cena = sc.getCena();
						break;
					}
				}
				System.out.println(cena);
				dto.setCena(cena);
				dto.setProsek(k);
				dto.setLekari(pronadjeniLekari);
				pronadjene.add(dto);
			}	
		}
		return new ResponseEntity<>(pronadjene, HttpStatus.OK);
	}

}
