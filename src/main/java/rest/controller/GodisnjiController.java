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
import rest.domain.GodisnjiOdmor;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.MedicinskaSestra;
import rest.domain.Pregled;
import rest.domain.User;
import rest.dto.GodisnjiDTO;
import rest.dto.PregledDTO;
import rest.service.GodisnjiService;
import rest.service.KlinikaService;
import rest.service.LekariService;
import rest.service.MSService;
import rest.service.MailService;
import rest.service.UserService;


@RestController

@RequestMapping("rest/godisnji")
public class GodisnjiController {
	@Autowired
	private KlinikaService klinikaService;
	@Autowired
	private GodisnjiService gService;
	@Autowired
	private LekariService lservice;
	@Autowired
	private UserService uservice;
	@Autowired
	private MSService msservice;
	@Autowired
	private MailService mailService;
	@GetMapping
	(value = "/svi/{id}")
	public ResponseEntity<List<GodisnjiDTO>> getGodisnjiKlinike(@PathVariable Integer id) throws ParseException {
		List<GodisnjiOdmor> god = gService.findAll();
		//System.out.println(sltermini.isEmpty());
		List<GodisnjiDTO> godto = new ArrayList<>();
		for (GodisnjiOdmor s : god) {
			Lekar lekar=lservice.findOne(s.getMedOsoblje().getId());
			if(lekar==null) {
				MedicinskaSestra ms=msservice.findOne(s.getMedOsoblje().getId());
				if(ms!=null) {
					if(ms.getKlinika().getId()==id  && s.getPrihvacenOdbijen()==null) {
						godto.add(new GodisnjiDTO(s));
					}
				}
			}else {
				if(lekar.getKlinika().getId()==id && s.getPrihvacenOdbijen()==null) {
					godto.add(new GodisnjiDTO(s));
				}
			}
				
		}
		return new ResponseEntity<>(godto, HttpStatus.OK);
	}
	@PostMapping(value="/prihvati",consumes = "application/json")
	public ResponseEntity<GodisnjiDTO> updateCourse(@RequestBody GodisnjiDTO gDTO) {

		System.out.println(gDTO.getRazlog());
		GodisnjiOdmor god = gService.findOne(gDTO.getId());

		if (god == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		god.setPrihvacenOdbijen(gDTO.getPrihvacenOdbijen());
		String mail=god.getMedOsoblje().getEmail();
		String naslov="Zahtev za godisnji odmor-";
		if(god.getPrihvacenOdbijen()) {
			naslov+="Prihvacen";
		}else {
			naslov+="Odbijen";
		}
		String tekst=gDTO.getRazlog();
		mailService.SendMail(mail, naslov, tekst);
		god=gService.save(god);
		return new ResponseEntity<>(gDTO, HttpStatus.OK);
	}/*
	@PostMapping(value = "/obrisi")
	public ResponseEntity<Void> deleteCourse(@RequestBody SalaPK id) {
		Sala sala = salaService.findOne(id);
		System.out.println("brisanje");
		List<Pregled> ztermini = pregledService.findZauzete(sala);
		System.out.println(ztermini.isEmpty());
		if (ztermini.isEmpty()){
			System.out.println("brisanje");
			if (sala != null) {
				salaService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	@PostMapping(value="/posalji",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> dodajGodisnji(@RequestBody GodisnjiDTO g){
		User u=uservice.findOne(g.getMedOsoblje_id());
		GodisnjiOdmor go= new GodisnjiOdmor(g.getId(),g.getDatumPocetka(),g.getDatumKraja(),g.getPrihvacenOdbijen(),u);

		gService.save(go);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


