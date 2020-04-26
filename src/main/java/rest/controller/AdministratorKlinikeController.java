package rest.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

import rest.domain.AdministratorKlinike;
import rest.domain.Sala;
import rest.domain.User;
import rest.dto.AdministratorKlinikeDTO;
import rest.service.AdminKService;



@RestController

@RequestMapping("rest/adminK")
public class AdministratorKlinikeController {
	@Autowired
	
	private AdminKService adminKService;

	@PutMapping(value="/izmeni",consumes = "application/json")
	public ResponseEntity<AdministratorKlinikeDTO> updateCourse(@RequestBody AdministratorKlinikeDTO admKDTO) {

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
		System.out.println("IDEMO3");
		admk= adminKService.save(admk);
		System.out.println("IDEMO4");
		return new ResponseEntity<>(new AdministratorKlinikeDTO(admk), HttpStatus.OK);
	}

}


