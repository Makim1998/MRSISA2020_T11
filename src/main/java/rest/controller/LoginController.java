package rest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rest.domain.AdministratorKlinike;
import rest.domain.Klinika;
import rest.domain.Lekar;
import rest.domain.Pacijent;
import rest.domain.Uloga;
import rest.domain.User;
import rest.service.AdminKService;
import rest.service.LekariService;
import rest.dto.KlinikaDTO;
import rest.dto.PacijentDTO;
import rest.service.PacijentService;
import rest.service.UserService;

/*
 * @RestController je anotacija nastala od @Controller tako da predstavlja bean komponentu.
 * 
 * @RequestMapping anotacija ukoliko se napise iznad kontrolera oznacava da sve rute ovog kontrolera imaju navedeni prefiks. 
 * U nasem primeru svaka rute kontrolera ima prefiks 'api/greetings'.
 */
@RestController
@RequestMapping("/rest/login")
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private PacijentService patientService;
	@Autowired
	private LekariService lekarService;
	@Autowired
	private AdminKService adminKService;
	
	private User logedIn;
	
	@PostConstruct
	public void init(){
		userService.findAll();
	}
	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @@GetMapping anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju GET zahteva
	 * atribut 'produce' sa naznakom tipa odgovora (u nasem slucaju JSON).
	 * 
	 * Kao povratna vrednost moze se vracati klasa ResponseEntity koja sadrzi i telo
	 * (sam podatak) i zaglavlje (metapodatke) i status kod, ili samo telo ako se
	 * metoda anotira sa @ResponseBody.
	 * 
	 * url: /api/greetings GET
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		Collection<User> users = userService.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	/*
	 * U viticastim zagradama se navodi promenljivi deo putanje.
	 * 
	 * url: /api/greetings/1 GET
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getGreeting(@PathVariable("id") String id) {
		User user = userService.findByEmail(id);

		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara
	 * unutar @PostMappimng anotacije: url kao vrednost 'value' atributa (ukoliko se
	 * izostavi, ruta do metode je ruta do kontrolera), u slucaju POST zahteva
	 * atribut 'produces' sa naznakom tipa odgovora (u nasem slucaju JSON) i atribut
	 * consumes' sa naznakom oblika u kojem se salje podatak (u nasem slucaju JSON).
	 * 
	 * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON
	 * podatka da napravi objekat tipa Greeting.
	 * 
	 * url: /api/greetings POST
	 */


	/*
	 * url: /api/greetings/1 PUT
	 */
	
	@GetMapping(value="/getUser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getlogedUser()
			throws Exception {
		return new ResponseEntity<User>(logedIn, HttpStatus.OK);
	}
	@GetMapping(value="/odjava")
	public ResponseEntity<User> Odjava() 
		throws Exception {
	logedIn = null;
	return new ResponseEntity<User>(logedIn, HttpStatus.OK);
	}
	@PostMapping(value = "register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody PacijentDTO pacijent)
			throws Exception {
		System.out.println("registrovanje pacijenta");
		if(pacijent == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		Pacijent p1 = patientService.findByEmail(pacijent.getEmail());
		Pacijent p2 = patientService.findByBrojOsiguranika(pacijent.getBrojOsiguranika());
		System.out.println(pacijent.getEmail()+ " " + pacijent.getPassword());
		if(p1 != null) {
			System.out.println("Kor. ime zauzeto");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if(p2 != null) {
			System.out.println("Broj osiguranika zauzet");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if(pacijent.getBrojOsiguranika().length() != 13) {
			System.out.println("Nevalidan jbr osiguranika!");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		Pacijent p= new Pacijent();
		p.setEmail(pacijent.getEmail());
		p.setPassword(pacijent.getPassword());
		p.setIme(pacijent.getIme());
		p.setPrezime(pacijent.getPrezime());
		p.setUloga(Uloga.PACIJENT);
		System.out.println("Validni podaci");
		p.setAdresa(pacijent.getAdresa());
		p.setEmail(pacijent.getEmail());
		p.setBrojOsiguranika(pacijent.getBrojOsiguranika());
		p.setDrzava(pacijent.getDrzava());

		p.setGrad(pacijent.getGrad());
		p.setPassword(pacijent.getPassword());
		System.out.println(p.getEmail());
		patientService.save(p);
		return new ResponseEntity<User>(p, HttpStatus.OK);
	}

	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User user)
			throws Exception {
		System.out.println("logovanje");
		System.out.println("halo");
		User u = userService.findByEmail(user.getUsername());
		
		System.out.println(user.getUsername()+ " " + user.getPassword());
		if(u == null) {
			System.out.println("nije pronasao korisnicko ime");

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if(!(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))) {
			System.out.println("nije pronasao par ime-lozinka");

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		System.out.println(u.getUloga());
		logedIn = u;
		return new ResponseEntity<User>(logedIn, HttpStatus.OK);
	}

	/*
	 * url: /api/greetings/1 DELETE
	 */
	@GetMapping(value = "/getConcreteUser/Pacijent")
	public ResponseEntity<User> isLogedPacijent() {
		if(logedIn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logedIn.getUloga()==Uloga.PACIJENT){
			Pacijent p = patientService.findByEmail(logedIn.getEmail());
			//System.out.println("Ulogovan pacijent");
			System.out.println(p.getAdresa()+"SSSSSSSSSS");
			return new ResponseEntity<User>(p, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/getConcreteUser/AdminK")
	public ResponseEntity<AdministratorKlinike> isLogedAdminK() {
		if(logedIn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logedIn.getUloga()==Uloga.ADMINISTRATOR_KLINIKE){
			AdministratorKlinike ak=adminKService.findByEmail(logedIn.getEmail());
			System.out.println("QQQQQQQQQQ");
			return new ResponseEntity<AdministratorKlinike>(ak, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/getConcreteUser/AdminKC")
	public ResponseEntity<User> isLogedAdminKC() {
		if(logedIn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logedIn.getUloga()==Uloga.ADMINISTRATOR_KLINICKOG_CENTRA){
			return new ResponseEntity<>( HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/getConcreteUser/Lekar")
	public ResponseEntity<User> isLogedLekar() {
		if(logedIn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logedIn.getUloga()==Uloga.LEKAR){
			Lekar l=lekarService.findByEmail(logedIn.getEmail());
			return new ResponseEntity<User>(l, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/getConcreteUser/MedicinskaS")
	public ResponseEntity<User> isLogedMedicinskaS() {
		if(logedIn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logedIn.getUloga()==Uloga.MEDICINSKA_SESTRA){
			return new ResponseEntity<>( HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/getKlinika")
	public ResponseEntity<KlinikaDTO> getKlinika() {
		if(logedIn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logedIn.getUloga()==Uloga.ADMINISTRATOR_KLINIKE){
			AdministratorKlinike ak=adminKService.findByEmail(logedIn.getEmail());
			Klinika klinika=ak.getKlinika();
			KlinikaDTO klinikaDTO=new KlinikaDTO(klinika);
			return new ResponseEntity<KlinikaDTO>(klinikaDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
