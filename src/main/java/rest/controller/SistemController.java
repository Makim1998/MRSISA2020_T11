package rest.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.Pregled;
import rest.service.PregledService;

@RestController
public class SistemController {

	@Autowired
	private PregledService pregledService;
	@Autowired
	private PregledController pregledController;
	
	@Scheduled(cron = "${greeting.cron}")
	public void cronJob() throws ParseException {
		dodjelaSalePregled();
	}
	private void dodjelaSalePregled() throws ParseException {
		List<Pregled> zahtijevi = pregledService.findZakazane();
		for (Pregled zahtijev : zahtijevi) {
			Pregled pregled=pregledController.algoritamPregled(zahtijev);
			pregledService.save(pregled);
		}
	}
}
