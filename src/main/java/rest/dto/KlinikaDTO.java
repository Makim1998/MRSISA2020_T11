package rest.dto;

import rest.domain.Klinika;

public class KlinikaDTO {
	
	public Integer id;
	public String naziv;
	public String adresa;
	public String opis;
	
	public KlinikaDTO(Klinika klinika) {
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.adresa = klinika.getAdresa();
		this.opis = klinika.getOpis();
	}

}
