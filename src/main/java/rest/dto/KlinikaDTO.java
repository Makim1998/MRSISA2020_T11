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
		System.out.println(klinika.getOcene());
		if(klinika.getOcene().size() == 0) {
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

	public KlinikaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	

}
