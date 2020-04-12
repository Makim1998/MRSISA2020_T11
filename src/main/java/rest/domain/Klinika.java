package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Klinika {
	
	@Id
	private int id;
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@OneToOne(mappedBy="klinika")
	private AdministratorKlinike administrator;
	@OneToOne(mappedBy="klinika")
	private Cenovnik cenovnik;
	@OneToMany(mappedBy="klinika")
	private Set<Sala> sale = new HashSet<Sala>();
	
	public AdministratorKlinike getAdministrator() {
		return administrator;
	}
	public void setAdministrator(AdministratorKlinike administrator) {
		this.administrator = administrator;
	}
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
	public Set<Sala> getSale() {
		return sale;
	}
	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}
	
	public Klinika(int id, String naziv, String adresa, String opis, Cenovnik cenovnik,
			AdministratorKlinike administrator, Set<Sala> sale) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.cenovnik = cenovnik;
		this.administrator = administrator;
		this.sale = sale;
	}
	
	public Klinika() {
	}
	
}
