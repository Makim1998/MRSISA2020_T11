package rest.dto;

import java.util.StringTokenizer;

import rest.domain.Sala;
import rest.pk.SalaPK;

public class SalaDTO {
	private Integer brojSale;
	private Integer klinika;
	public String naziv;

	
	public Integer getBrojSale() {
		return brojSale;
	}
	public void setBrojSale(Integer brojSale) {
		this.brojSale = brojSale;
	}
	public Integer getKlinika() {
		return klinika;
	}
	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}
	public SalaDTO(String linija) {
		StringTokenizer st = new StringTokenizer(linija, "-");
		this.naziv = st.nextToken().trim();
		//this.brojSale = Integer.parseInt(st.nextToken().trim().substring(3));
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public SalaDTO() {
		super();
	}
	public SalaDTO(Integer a, Integer b, String c) {
		this.brojSale=a;
		this.klinika=b;
		this.naziv=c;
	}
}
