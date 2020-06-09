package rest.controller;

import java.util.ArrayList;
import java.util.List;

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
import rest.dto.AdministratorKCentraDTO;
import rest.service.AdminKCService;

@RestController
@RequestMapping("rest/adminKC")
public class AdminKCentraController {
	
	@Autowired
	private AdminKCService service;

	@GetMapping
	public ResponseEntity<List<AdministratorKCentraDTO>> getAdmins(){
		List<AdministratorKCentraDTO> ret = new ArrayList<AdministratorKCentraDTO>();
		List<AdministratorKlinickogCentra> admins = service.findAll();
		for (AdministratorKlinickogCentra a: admins) {
			AdministratorKCentraDTO dto = new AdministratorKCentraDTO(a);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addAdmin(@RequestBody AdministratorKCentraDTO dto){
		dto.setPrviPut(true);
		AdministratorKlinickogCentra admin = new AdministratorKlinickogCentra(dto);
		System.out.println("Kreiran je novi admin KC");
		admin = service.save(admin);
		System.out.println("Dodat je novi admin KC");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<AdministratorKCentraDTO> updateCourse(@RequestBody AdministratorKCentraDTO admKDTO) {

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
}
