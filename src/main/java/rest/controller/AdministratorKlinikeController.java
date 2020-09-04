package rest.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import rest.domain.AdministratorKlinickogCentra;
import rest.domain.AdministratorKlinike;
import rest.domain.KlinickiCentar;
import rest.domain.Klinika;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.AdministratorKlinikeDTO;
import rest.service.AdminKCService;
import rest.service.AdminKService;
import rest.service.KlinikaService;


@RestController

@RequestMapping("rest/adminK")
public class AdministratorKlinikeController {
	@Autowired
	private AdminKService adminKService;
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private AdminKCService adminKCService;
	
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
	private KlinickiCentar getKC() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		AdministratorKlinickogCentra admin = adminKCService.findOne(logedIn.getId());
		return admin.getKlinickiCentar();
	}
	
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<AdministratorKlinikeDTO> updateCourse(@RequestBody AdministratorKlinikeDTO admKDTO) {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE && tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// a course must exist
		System.out.println("IDEMO1");
		AdministratorKlinike admk = adminKService.findOne(admKDTO.getId());

		if (admk == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("IDEMO2");
		admk.setIme(admKDTO.getIme());
		admk.setPrezime(admKDTO.getPrezime());
		admk.setAdresa(admKDTO.getAdresa());
		admk.setGrad(admKDTO.getGrad());
		admk.setDrzava(admKDTO.getDrzava());
		admk.setPassword(admKDTO.getPassword());
		admk.setPrviPut(admKDTO.getPrviPut());
		System.out.println("IDEMO3");
		admk= adminKService.save(admk);
		System.out.println("IDEMO4");
		return new ResponseEntity<>(new AdministratorKlinikeDTO(admk), HttpStatus.OK);
	}
	/*@GetMapping(value ="/getKlinika",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Klinika> getKlinika(@RequestParam Integer id){
		System.out.println("9092 "+id);
		AdministratorKlinike admk = adminKService.findOne(id);
		
		if (admk == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Klinika>(admk.getKlinika(), HttpStatus.OK);
	}*/

	@GetMapping
	public ResponseEntity<List<AdministratorKlinikeDTO>> getAdmins(){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE && tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<AdministratorKlinikeDTO> ret = new ArrayList<AdministratorKlinikeDTO>();
		List<AdministratorKlinike> admins = adminKService.findAll();
		for (AdministratorKlinike a: admins) {
			AdministratorKlinikeDTO dto = new AdministratorKlinikeDTO(a);
			if (a.getKlinika().getKlinickiCentar().getId() == getKC().getId())
				ret.add(dto);
			System.out.println(a.getKlinika().getNaziv() + ": " + a.getKlinika().getAdresa());
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addAdmin(@RequestBody AdministratorKlinikeDTO dto){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINIKE && tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		dto.setPrviPut(true);
		AdministratorKlinike admin = new AdministratorKlinike(dto);
		Klinika klinika = klinikaService.findOne(dto.getKc_id());
		admin.setKlinika(klinika);
		admin = adminKService.save(admin);
		klinika.setAdministrator(admin);
		klinika = klinikaService.save(klinika);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id){
		System.out.println("Stigne do backend-a za brisanje admina K");
		if (tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		AdministratorKlinickogCentra admin = adminKCService.findOne(logedIn.getId());
		System.out.println("admin KC id: "+admin.getId());
		if (admin.getId() != 1 && admin.getId() != 2 && admin.getId() != 3) { 
			System.out.println("Problem je kod ulogovanog");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		AdministratorKlinike brisan = adminKService.findOne(id);
		Klinika klinika = klinikaService.findOne(brisan.getKlinika().getId());
		klinika.setAdministrator(null);
		klinika = klinikaService.save(klinika);
		adminKService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
