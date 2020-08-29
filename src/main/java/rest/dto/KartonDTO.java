package rest.dto;

import java.text.SimpleDateFormat;

import rest.domain.Karton;
import rest.domain.Pol;

public class KartonDTO {
	private Integer id;
	private String datumRodjenja;
	private Pol pol;
	private String polStr;
	private String ime;
	private String prezime;
	private String krvnaGrupa;
	private String istorijaBolesti;
	private String visina;
	private String tezina;
	private String alergije;
	private String propisano;
	
	public KartonDTO() {
		
	}
	public KartonDTO(Karton k) {
		id = k.getId();
		String pattern = "dd.MM.yyyy.";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		datumRodjenja = simpleDateFormat.format(k.getDatumRodjenja());
		pol = k.getPol();
		polStr = k.getPol().name();
		ime = k.getIme();
		krvnaGrupa = k.getKrvnaGrupa();
		prezime = k.getPrezime();
		istorijaBolesti = k.getIstorijaBolesti();
		visina = k.getVisina();
		tezina = k.getTezina();
		alergije = k.getAlergije();
		propisano = k.getPropisano();
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
	public String getIstorijaBolesti() {
		return istorijaBolesti;
	}
	public void setIstorijaBolesti(String istorijaBolesti) {
		this.istorijaBolesti = istorijaBolesti;
	}
	public String getVisina() {
		return visina;
	}
	public void setVisina(String visina) {
		this.visina = visina;
	}
	public String getTezina() {
		return tezina;
	}
	public void setTezina(String tezina) {
		this.tezina = tezina;
	}
	public String getAlergije() {
		return alergije;
	}
	public void setAlergije(String alergije) {
		this.alergije = alergije;
	}
	public String getPropisano() {
		return propisano;
	}
	public void setPropisano(String propisano) {
		this.propisano = propisano;
	}
	public String getPolStr() {
		return polStr;
	}
	public void setPolStr(String polStr) {
		this.polStr = polStr;
	}

}
