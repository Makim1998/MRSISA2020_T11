package rest.dto;

import java.util.ArrayList;

import rest.domain.Klinika;
import rest.domain.Lekar;

public class KlinikaDTO {
	
	private Integer id;
	private String naziv;
	private String adresa;
	private String opis;
	public String prosecnaOcena;
	private ArrayList<LekarDTO> lekari = new ArrayList<LekarDTO>();
	
	public KlinikaDTO(Klinika klinika) {
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.adresa = klinika.getAdresa();
		this.opis = klinika.getOpis();
		this.lekari = new ArrayList<LekarDTO>();
		for (Lekar l: klinika.getLekari()) {
			LekarDTO dto =new LekarDTO(l);
			dto.setProsek(l);
			this.lekari.add(dto);
		}
		this.setProsek(klinika);
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
	
	public ArrayList<LekarDTO> getLekari() {
		return lekari;
	}


	public void setLekari(ArrayList<LekarDTO> lekari) {
		this.lekari = lekari;
	}
	

}
