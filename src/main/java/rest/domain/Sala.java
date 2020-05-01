package rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import rest.pk.SalaPK;

@Entity
@IdClass(SalaPK.class)
public class Sala {
	@ManyToOne
	@Id
	private Klinika klinika;
	@Id
	@Column(nullable=false)
	private Integer brojSale;
	@Column
	private String naziv;
	
	public Sala() {
		super();
	}
	public Sala(Klinika klinika,Integer id2, String naziv2) {
		this.klinika=klinika;
		brojSale=id2;
		naziv=naziv2;
	}
	public Integer getId() {
		return brojSale;
	}
	public void setId(Integer id) {
		this.brojSale = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Klinika getKlinika() {
		return klinika;
	}
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
}
