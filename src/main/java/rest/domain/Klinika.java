package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import rest.dto.KlinikaDTO;

@Entity
public class Klinika {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="naziv", unique = true, nullable = false)
	private String naziv;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private KlinickiCentar klinickiCentar;
	
	@Column(name ="adresa", unique = true, nullable = false)
	private String adresa;
	
	@Column(name ="opis", nullable = false)
	private String opis;
	
	@OneToOne(mappedBy="klinika",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AdministratorKlinike administrator;
	
	@OneToOne(mappedBy="klinika")
	private Cenovnik cenovnik;
	
	@OneToMany(mappedBy="klinika",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sala> sale = new HashSet<Sala>();
	
	@OneToMany(mappedBy="klinika",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	
	private Set<Lekar> lekari = new HashSet<Lekar>();
	
	@OneToMany(mappedBy="klinika",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicinskaSestra> medicinskeSestre = new HashSet<MedicinskaSestra>();
	
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
	
	public Klinika(KlinikaDTO dto) {
		this.id = dto.id;
		this.adresa = dto.adresa;
		this.naziv = dto.naziv;
		this.opis = dto.opis;
		this.administrator = new AdministratorKlinike();
		this.cenovnik = new Cenovnik();
	}
	
}
