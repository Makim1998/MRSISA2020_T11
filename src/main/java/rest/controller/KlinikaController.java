package rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.GodisnjiOdmor;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.Pregled;
import rest.dto.KlinikaDTO;
import rest.dto.LekarDTO;
import rest.service.KlinikaService;
import rest.service.PregledService;


@RestController
@RequestMapping("rest/klinika")
public class KlinikaController {
	
	@Autowired
	private KlinikaService service;
	
	@Autowired
	private PregledService pregledi;
	

	
	@GetMapping
	public ResponseEntity<List<KlinikaDTO>> getKlinike(){
		List<Klinika> klinike = service.findAll();
		List<KlinikaDTO> ret = new ArrayList<KlinikaDTO>();
		for (Klinika k: klinike) {
			KlinikaDTO dto = new KlinikaDTO(k);
			dto.setProsek(k);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<KlinikaDTO> updateCourse(@RequestBody KlinikaDTO kDTO) {

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
		System.out.println("naziv: " + klinikaDto.getNaziv());
		System.out.println("adresa: " + klinikaDto.getAdresa());
		System.out.println("opis: " + klinikaDto.getOpis());
		Klinika klinika = new Klinika(klinikaDto);
		System.out.println("Kreirana je nova klinika");
		klinika = service.save(klinika);
		System.out.println("Dodata je nova klinika");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/adminIds")
	public ResponseEntity<List<Integer>> getAdminss(){
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
		List<Klinika> klinike = service.findAll();
		List<Integer> ids = new ArrayList<Integer>();
		for (Klinika k: klinike) {
			if (k.getCenovnik() == null)
				ids.add(k.getId());
		}
		return new ResponseEntity<>(ids, HttpStatus.OK);
	}
	
	@PostMapping(value="/ocena",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KlinikaDTO> ocene(@RequestParam String klinika,@RequestParam String ocena){
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
				dto.setProsek(k);
				dto.setLekari(pronadjeniLekari);
				pronadjene.add(dto);
			}	
		}
		return new ResponseEntity<>(pronadjene, HttpStatus.OK);
	}

}
