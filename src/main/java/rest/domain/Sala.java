  package rest.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
	
	@OneToMany(mappedBy="sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("datum ASC")
	private List<Pregled> pregledi= new ArrayList<Pregled>();
	
	@OneToMany(mappedBy="sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("datum ASC")
	private List<Operacija> operacije= new ArrayList<Operacija>();
	
	public Sala() {
		super();
	}
	public Sala(Klinika klinika,Integer id2, String naziv2) {
		this.klinika=klinika;
		brojSale=id2;
		naziv=naziv2;
	}
	public Integer getBrojSale() {
		return brojSale;
	}
	
	public List<Operacija> getOperacije() {
		return operacije;
	}
	public void setOperacije(List<Operacija> operacije) {
		this.operacije = operacije;
	}
	public void setBrojSale(Integer brojSale) {
		this.brojSale = brojSale;
	}
	public List<Pregled> getPregledi() {
		return pregledi;
	}
	public void setPregledi(List<Pregled> pregledi) {
		this.pregledi = pregledi;
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
