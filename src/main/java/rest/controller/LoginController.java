package rest.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.User;
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
		User user = userService.findOne(id);

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
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User user)
			throws Exception {
		User u = userService.findOne(user.getUsername());
		System.out.println("logovanje");
		System.out.println(user.getUsername()+ " " + user.getPassword());
		if(u == null) {
			System.out.println("nije pronasao korisnicko ime");

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if(!(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))) {
			System.out.println("nije pronasao par ime-lozinka");

			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/*
	 * url: /api/greetings/1 DELETE
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> deleteGreeting(@PathVariable("id") String id) {
		userService.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}