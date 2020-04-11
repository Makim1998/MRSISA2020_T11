package rest.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Klinika {
	
	@Id
	private int id;
	private String naziv;
	private String adresa;
	private String opis;
	private Cenovnik cenovnik;
	private ArrayList<Sala> sale;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Cenovnik getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}
	public ArrayList<Sala> getSale() {
		return sale;
	}
	public void setSale(ArrayList<Sala> sale) {
		this.sale = sale;
	}
	
	public Klinika(String naziv, String adresa, String opis, Cenovnik cenovnik, ArrayList<Sala> sale) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.cenovnik = cenovnik;
		this.sale = sale;
	}
	
	public Klinika() {
	}
	
}
