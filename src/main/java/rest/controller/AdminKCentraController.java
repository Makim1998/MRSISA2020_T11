package rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.AdministratorKlinickogCentra;
import rest.domain.KlinickiCentar;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.AdministratorKCentraDTO;
import rest.service.AdminKCService;
import rest.service.KlinickiCentarService;

@RestController
@RequestMapping("rest/adminKC")
public class AdminKCentraController {
	
	@Autowired
	private AdminKCService service;
	
	@Autowired
	private KlinickiCentarService kcService;
	
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
	private KlinickiCentar getKC() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		AdministratorKlinickogCentra admin = service.findOne(logedIn.getId());
		return kcService.findOne(admin.getKlinickiCentar().getId());
	}

	@GetMapping
	public ResponseEntity<List<AdministratorKCentraDTO>> getAdmins(){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<AdministratorKCentraDTO> ret = new ArrayList<AdministratorKCentraDTO>();
		List<AdministratorKlinickogCentra> admins = service.findAll();
		for (AdministratorKlinickogCentra a: admins) {
			AdministratorKCentraDTO dto = new AdministratorKCentraDTO(a);
			if (getKC().getId() == a.getKlinickiCentar().getId())
				ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addAdmin(@RequestBody AdministratorKCentraDTO dto){
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		dto.setPrviPut(true);
		AdministratorKlinickogCentra admin = new AdministratorKlinickogCentra(dto);
		System.out.println("Kreiran je novi admin KC");
		admin.setKlinickiCentar(getKC());
		admin = service.save(admin);
		System.out.println("Dodat je novi admin KC");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<AdministratorKCentraDTO> updateCourse(@RequestBody AdministratorKCentraDTO admKDTO) {
		if(tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		AdministratorKlinickogCentra admk = service.findOne(admKDTO.getId());

		if (admk == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		admk.setIme(admKDTO.getIme());
		admk.setPrezime(admKDTO.getPrezime());
		admk.setAdresa(admKDTO.getAdresa());
		admk.setGrad(admKDTO.getGrad());
		admk.setDrzava(admKDTO.getDrzava());
		admk.setPassword(admKDTO.getPassword());
		admk= service.save(admk);
		return new ResponseEntity<>(new AdministratorKCentraDTO(admk), HttpStatus.OK);
	}
	
	@GetMapping(value="/inicijalni")
	public ResponseEntity<Boolean> getInitial(){
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		AdministratorKlinickogCentra admin = service.findOne(logedIn.getId());
		if (admin.getId() == 1)
			return new ResponseEntity<>(true, HttpStatus.OK);
		else if (admin.getId() == 2)
			return new ResponseEntity<>(true, HttpStatus.OK);
		else if (admin.getId() == 3)
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
