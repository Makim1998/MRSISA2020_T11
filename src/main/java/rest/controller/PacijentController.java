package rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Karton;
import rest.domain.Lekar;
import rest.domain.Operacija;
import rest.domain.Pacijent;
import rest.domain.Pregled;
import rest.domain.Uloga;
import rest.domain.User;
import rest.dto.KartonDTO;
import rest.dto.PacijentDTO;
import rest.service.KartonService;
import rest.service.LekariService;
import rest.service.MailService;
import rest.service.PacijentService;

@RestController
@RequestMapping("/rest/pacijent")
public class PacijentController {
	@Autowired
	private PacijentService patientService;
	@Autowired
	private KartonService kartonService;
	@Autowired
	private LekariService lekarService;
	@Autowired
	public HttpServletRequest request;
	@Autowired
	private MailService mailService;
	
	private Uloga tipKorisnika() {
		User logedIn = (User) request.getSession().getAttribute("korisnik");
		return logedIn.getUloga();
	}
	
	@GetMapping(value ="/svi", produces = "application/json")
	public ResponseEntity<List<PacijentDTO>> getPacijenti() {
		
		List<Pacijent> pacijenti = patientService.findAll();

		List<PacijentDTO> pacijentiDTO = new ArrayList<>();
		for (Pacijent s : pacijenti) {
			if (s.getOdobren() == Boolean.TRUE)
				pacijentiDTO.add(new PacijentDTO(s));
		}

		return new ResponseEntity<>(pacijentiDTO, HttpStatus.OK);
	}
	
	@GetMapping(value ="/getKarton", produces = "application/json")
	public ResponseEntity<KartonDTO> getKarton(@RequestParam String email)
			throws Exception {
		System.out.println("pregled kartona - pacijent");
		System.out.println(email);
		Pacijent p = patientService.findByEmail(email);
		
		System.out.println(email);
		if(p == null) {
			System.out.println("nije pronasao korisnicko ime pacijenta");

			return new ResponseEntity<KartonDTO>(HttpStatus.BAD_REQUEST);
		}
		if(p.getKarton() == null) {
			System.out.println("nije kreiran karton za pacijenta");
			return new ResponseEntity<KartonDTO>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<KartonDTO>(new KartonDTO(p.getKarton()), HttpStatus.OK);
	}
	@GetMapping
	(value = "/pregledani/{id}")
	public ResponseEntity<List<PacijentDTO>> getPregledaniPacijenti(@PathVariable Integer id) throws ParseException {
		if(tipKorisnika()!=Uloga.LEKAR) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("flamingosi0");
		Lekar l=lekarService.findOne(id);
		List<PacijentDTO> slterminiDTO = new ArrayList<>();
		ArrayList<Integer> kartoni=new ArrayList<Integer>();
		kartoni.add(0);
		for (Operacija op : l.getOperacije()) {
			System.out.println("flamingosi1");
			boolean postoji=false;
			Pacijent p=patientService.findOneByKarton(op.getKarton());
			try {
				Integer idk=op.getKarton().getId();
				for (int i: kartoni) {
					if(idk==i) {
						postoji=true;
					}
				}
				if(postoji==false) {
					slterminiDTO.add(new PacijentDTO(p));
					kartoni.add(idk);
				}
			} catch (Exception e) {
				System.out.println("flamingos");
			}
		}
		System.out.println("flamingosi2");
		for (Pregled op : l.getPregledi()) {
			System.out.println("flamingosi3");
			boolean postoji=false;
			Pacijent p=patientService.findOneByKarton(op.getKarton());
			try {
				Integer idk=op.getKarton().getId();
				for (int i: kartoni) {
					if(idk==i) {
						postoji=true;
					}
				}
				if(postoji==false) {
					slterminiDTO.add(new PacijentDTO(p));
					kartoni.add(idk);
				}
			} catch (Exception e) {
				System.out.println("flamingos");
			}
		}
		return new ResponseEntity<>(slterminiDTO, HttpStatus.OK);
	}	
	@PutMapping(value ="/profil",consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> editProfile(@RequestBody PacijentDTO pacijent)
			throws Exception {
		if(tipKorisnika()!=Uloga.PACIJENT && tipKorisnika()!=Uloga.ADMINISTRATOR_KLINICKOG_CENTRA ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
		if(p.getKarton()!= null) {
			p.getKarton().setIme(pacijent.getIme());
			p.getKarton().setPrezime(pacijent.getPrezime());
		}
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
	
	@PutMapping(value="/prihvati/{id}")
	public ResponseEntity<Void> prihvati(@PathVariable Integer id){
		System.out.println("Stiglo je do backend-a za prihvatanje zahteva za registraciju");
		if (tipKorisnika() != Uloga.ADMINISTRATOR_KLINICKOG_CENTRA)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Pacijent pacijent = patientService.findOne(id);
		pacijent.setOdobren(Boolean.TRUE);
		patientService.save(pacijent);
		String email = pacijent.getEmail();
		String subject = "Potvrdena registracija";
		String poruka = "Postovani,\n\nObavestavamo Vas da je prihvacen Vas zahtev "
				+ "za registraciju na nas klinicki centar.";
		mailService.SendMail(email, subject, poruka);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/odbij/{id}/{razlog}")
	public ResponseEntity<Void> odbij(@PathVariable Integer id, @PathVariable String razlog){
		System.out.println("Stiglo je do backend-a za odbijanje zahteva za registraciju");
		if (tipKorisnika() != Uloga.ADMINISTRATOR_KLINICKOG_CENTRA)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		String email = patientService.findOne(id).getEmail();
		String subject = "Odbijena registracija";
		String poruka = "Postovani,\n\nObavestavamo Vas da je odbijen Vas zahtev "
				+ "za registraciju na nas klinicki centar.\n\n"
				+ "Razlog:\n" + razlog;
		mailService.SendMail(email, subject, poruka);
		patientService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/zahtevi")
	public ResponseEntity<List<PacijentDTO>> zahtevi(){
		
		System.out.println("Stiglo je do prikupljanja zahteva");
		List<Pacijent> pacijenti = patientService.findAll();

		List<PacijentDTO> pacijentiDTO = new ArrayList<PacijentDTO>();
		for (Pacijent s : pacijenti) {
			if (s.getOdobren() == Boolean.FALSE)
				pacijentiDTO.add(new PacijentDTO(s));
		}

		return new ResponseEntity<>(pacijentiDTO, HttpStatus.OK);
	}
	
	@PutMapping(value="/createKarton/{id}")
	public ResponseEntity<Void> createKarton(@RequestBody KartonDTO karton, @PathVariable Integer id) throws ParseException{
		System.out.println(karton.getPolStr());
		System.out.println(karton.getIme() + " " + karton.getPrezime());
		System.out.println(karton.getDatumRodjenja());
		SimpleDateFormat sdf = new SimpleDateFormat("DD.MM.YYYY.");
		Karton k = new Karton();
		k.setIme(karton.getIme());
		k.setKrvnaGrupa(karton.getKrvnaGrupa());
		k.setPol(karton.getPolStr());
		k.setPrezime(karton.getPrezime());
		k.setTezina(karton.getTezina());
		k.setVisina(karton.getVisina());
		k.setPropisano("nema propisane lekove");
		if (karton.getAlergije().equals(""))
			k.setAlergije("nema alergije");
		else
			k.setAlergije(karton.getAlergije());
		if (karton.getIstorijaBolesti().equals(""))
			k.setIstorijaBolesti("Nije bilo nikakvih bolesti");
		else
			k.setIstorijaBolesti(karton.getIstorijaBolesti());
		k.setDatumRodjenja(sdf.parse(karton.getDatumRodjenja()));
		Pacijent p = patientService.findOne(id);
		p.setKarton(k);
		k.setPacijent(p);
		k = kartonService.save(k);
		p = patientService.save(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
