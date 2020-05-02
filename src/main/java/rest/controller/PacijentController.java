package rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Karton;
import rest.domain.Lekar;
import rest.domain.Pacijent;
import rest.domain.User;
import rest.dto.LekarDTO;
import rest.dto.PacijentDTO;
import rest.service.PacijentService;

@RestController
@RequestMapping("/rest/pacijent")
public class PacijentController {
	@Autowired
	private PacijentService patientService;
	
	@GetMapping(value ="/svi", produces = "application/json")
	public ResponseEntity<List<PacijentDTO>> getPacijenti() {
		
		List<Pacijent> pacijenti = patientService.findAll();

		List<PacijentDTO> pacijentiDTO = new ArrayList<>();
		for (Pacijent s : pacijenti) {
			pacijentiDTO.add(new PacijentDTO(s));
		}

		return new ResponseEntity<>(pacijentiDTO, HttpStatus.OK);
	}
	
	@GetMapping(value ="/getKarton", produces = "application/json")
	public ResponseEntity<Karton> getKarton(@RequestParam String email)
			throws Exception {
		System.out.println("pregled kartona - pacijent");
		System.out.println(email);
		Pacijent p = patientService.findByEmail(email);
		
		System.out.println(email);
		if(p == null) {
			System.out.println("nije pronasao korisnicko ime pacijenta");

			return new ResponseEntity<Karton>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Karton>(p.getKarton(), HttpStatus.OK);
	}
	
	@PutMapping(value ="/profil",consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> editProfile(@RequestBody PacijentDTO pacijent)
			throws Exception {
		System.out.println("izmena profila - pacijent");
		Pacijent p = patientService.findByEmail(pacijent.getEmail());
		
		System.out.println(pacijent.getEmail()+ " " + pacijent.getPassword());
		if(p == null) {
			System.out.println("nije pronasao korisnicko ime pacijenta");

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		 
		if(!proveri(p)) {
			System.out.println("nevalidne vrednosti izmenjenih polja");

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		p.setIme(pacijent.getIme());
		p.setPrezime(pacijent.getPrezime());
		p.setAdresa(pacijent.getAdresa());
		p.setDrzava(pacijent.getDrzava());
		p.setGrad(pacijent.getGrad());
		patientService.save(p);
		
		return new ResponseEntity<User>(p, HttpStatus.OK);
	}


	private boolean proveri(Pacijent p) {
		if(p.getAdresa().equals("") || p.getDrzava().equals("") || p.getGrad().equals("") || p.getIme().equals("") || p.getPrezime().equals("")){ 
			System.out.println("Ne moze to tako");;
			return false;
		}
		return true;
	}
}
