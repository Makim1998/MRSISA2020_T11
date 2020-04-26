package rest.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Karton {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "datumRodjenja",nullable = false)
	private Date datumRodjenja;
	
	@Column(name = "pol",nullable = false)
	private Pol pol;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(name = "krvnaGrupa",nullable = false)
	private String krvnaGrupa;
	
	@OneToOne(mappedBy = "karton")
	@JsonIgnore
	private Pacijent pacijent;
	
	@OneToMany(mappedBy="karton", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Pregled> pregledi =new HashSet<Pregled>();
	
	public Set<Pregled> getPregledi() {
		return pregledi;
	}
	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
	public Karton() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}
	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}
	public Pacijent getPacijent() {
		return pacijent;
	}
	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
}
