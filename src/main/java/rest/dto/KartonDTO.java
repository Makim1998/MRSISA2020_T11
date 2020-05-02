package rest.dto;

import java.text.SimpleDateFormat;

import rest.domain.Karton;
import rest.domain.Pol;

public class KartonDTO {
	private Integer id;
	private String datumRodjenja;
	private Pol pol;
	private String ime;
	private String prezime;
	private String krvnaGrupa;
	
	public KartonDTO() {
		
	}
	public KartonDTO(Karton k) {
		id = k.getId();
		String pattern = "dd.MM.yyyy.";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		datumRodjenja = simpleDateFormat.format(k.getDatumRodjenja());
		pol = k.getPol();
		ime = k.getIme();
		krvnaGrupa = k.getKrvnaGrupa();
		prezime = k.getPrezime();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}
	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}
	
	


}

