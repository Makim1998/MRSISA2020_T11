package rest.dto;

import rest.domain.TipPregleda;

public class TipPregledaDTO {

	private Long id;
	public String naziv;

	public TipPregledaDTO(Long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}

	public TipPregledaDTO(TipPregleda tipPregleda) {
		this(tipPregleda.getId(),tipPregleda.getNaziv());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
