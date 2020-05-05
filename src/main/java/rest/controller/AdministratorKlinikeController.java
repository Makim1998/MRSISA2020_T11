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

import rest.domain.AdministratorKlinike;
import rest.domain.Klinika;
import rest.dto.AdministratorKlinikeDTO;
import rest.service.AdminKService;
import rest.service.KlinikaService;


@RestController

@RequestMapping("rest/adminK")
public class AdministratorKlinikeController {
	@Autowired
	private AdminKService adminKService;
	
	@Autowired
	private KlinikaService klinikaService;
	
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
		List<AdministratorKlinikeDTO> ret = new ArrayList<AdministratorKlinikeDTO>();
		List<AdministratorKlinike> admins = adminKService.findAll();
		for (AdministratorKlinike a: admins) {
			AdministratorKlinikeDTO dto = new AdministratorKlinikeDTO(a);
			ret.add(dto);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value="/dodaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdministratorKlinike> addAdmin(@RequestBody AdministratorKlinikeDTO dto){
		AdministratorKlinike admin = new AdministratorKlinike(dto);
		Klinika klinika = klinikaService.findOne(dto.getKc_id());
		admin.setKlinika(klinika);
		adminKService.save(admin);
		klinika.setAdministrator(admin);
		klinikaService.save(klinika);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
}
