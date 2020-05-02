package rest.dto;

import rest.domain.Klinika;

public class KlinikaDTO {
	
	public Integer id;
	public String naziv;
	public String adresa;
	public String opis;
	public String prosecnaOcena;
	
	public KlinikaDTO(Klinika klinika) {
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.adresa = klinika.getAdresa();
		this.opis = klinika.getOpis();
	}
	
	public void setProsek(Klinika klinika) {
		if(klinika.getOcene() == null) {
			prosecnaOcena = "nema";
		}
		else {
			double prosek = 0.;
			for(Integer i:klinika.getOcene()) {
				prosek += i;
			}
			prosek /= klinika.getOcene().size();
			prosecnaOcena = String.format("%.2f", prosek);
		}
	}

}
