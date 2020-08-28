package rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Karton;
import rest.domain.Lek;
import rest.domain.MedicinskaSestra;
import rest.domain.Recept;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.ReceptDTO;
import rest.service.KartonService;
import rest.service.MSService;
import rest.service.ReceptService;

@RestController
@RequestMapping("rest/recept")
public class ReceptController {
	
	@Autowired
	private ReceptService service;
	
	@Autowired
	private MSService msService;
	
	@Autowired
	private KartonService kartonService;
	
	@Autowired
	public HttpServletRequest request;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
	private MedicinskaSestra getMS() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		MedicinskaSestra ms = msService.findOne(logedIn.getId());
		return ms;
	}
	
	@GetMapping
	public ResponseEntity<List<ReceptDTO>> getRecepts(){
		System.out.println("Stigne do backend-a za overavanje");
		if (tipKorisnika() != Uloga.MEDICINSKA_SESTRA) {
			new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		List<ReceptDTO> ret = new ArrayList<ReceptDTO>();
		for (Recept recept: service.findAll()) {
			ReceptDTO dto = new ReceptDTO(recept);
			String lekovi = "";
			if (!recept.getLekovi().isEmpty()) {
				for (Lek lek: recept.getLekovi()) {
					lekovi += lek.getNaziv() + ", ";
				}
				lekovi = lekovi.substring(0, lekovi.length()-2);
			}
			else
				lekovi = "nema lekova";
			dto.setLekovi(lekovi);
			if (recept.getSestra() == null)
				ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> odobriRecept(@PathVariable Integer id){
		if (tipKorisnika() != Uloga.MEDICINSKA_SESTRA) {
			new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		MedicinskaSestra ms = getMS();
		Recept recept = service.findOne(id);
		recept.setSestra(ms);
		recept = service.save(recept);
		System.out.println(recept.getDijagnoza().getOpis());
		System.out.println(recept.getDijagnoza().getPregled().getCena().getCena());
		System.out.println(recept.getDijagnoza().getPregled().getKarton().getKrvnaGrupa());
		Karton karton = kartonService.findOne(recept.getDijagnoza().getPregled().getKarton().getId());
		String propisano = "";
		if (!recept.getLekovi().isEmpty()) {
			for (Lek lek: recept.getLekovi()) {
				propisano += lek.getNaziv() + ", ";
			}
			propisano = propisano.substring(0, propisano.length()-2);
		}
		else
			propisano = "nema lekova";
		karton.setPropisano(propisano);
		karton = kartonService.save(karton);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
