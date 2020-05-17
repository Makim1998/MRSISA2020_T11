package rest.domain;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rest.dto.LekarDTO;
import rest.dto.OperacijaDTO;

@Entity 
public class Operacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "datum",unique = false, nullable = false)
	private Date datum;
	
	@Column(name = "trajanje",unique = false, nullable = false)
	private int trajanje;
	
	@ManyToOne
	private Karton karton;
	
	@OneToOne
	private StavkaCenovnika cena;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sala sala;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Lekar> lekari=new HashSet<Lekar>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public Karton getKarton() {
		return karton;
	}
	public void setKarton(Karton karton) {
		this.karton = karton;
	}
	public StavkaCenovnika getCena() {
		return cena;
	}
	public void setCena(StavkaCenovnika cena) {
		this.cena = cena;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Lekar> getLekari() {
		return lekari;
	}
	public void setLekari(HashSet<Lekar> lekari) {
		this.lekari = lekari;
	}
	public Operacija() {
		super();
	}
	public Operacija(OperacijaDTO oper,StavkaCenovnika st,Sala s, TipPregleda t) {
		this.datum=oper.getDatum();
		this.trajanje=oper.getTrajanje();
		this.cena=st;
		this.lekari=null;
		this.sala=s;
	}
}

