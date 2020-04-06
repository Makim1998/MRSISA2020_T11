package rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.domain.TipPregleda;
import rest.domain.TipPregledaDTO;
import rest.service.TipPregledaService;

@RestController
@RequestMapping("rest/tipPregleda")
public class TipPregledaController {
	@Autowired
	private TipPregledaService tipPregledaService;

	@GetMapping
	public ResponseEntity<List<TipPregledaDTO>> getTipPregleda() {
		
		List<TipPregleda> tipovi = tipPregledaService.findAll();

		List<TipPregledaDTO> tipoviDTO = new ArrayList<>();
		for (TipPregleda s : tipovi) {
			tipoviDTO.add(new TipPregledaDTO(s));
		}

		return new ResponseEntity<>(tipoviDTO, HttpStatus.OK);
	}
}
