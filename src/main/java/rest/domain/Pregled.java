package rest.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rest.dto.LekarDTO;
import rest.dto.PregledDTO;

@Entity 
public class Pregled implements Comparable<Pregled> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "datum",unique = false, nullable = false)
	private Date datum;
	
	@Column(name = "trajanje",unique = false, nullable = false)
	private int trajanje;
	
	@ManyToOne
	@JoinColumn(name="tip_id", referencedColumnName="id", nullable=false)
	private TipPregleda tip;
	
	@ManyToOne
	private Karton karton;
	
	@OneToOne
	private StavkaCenovnika cena;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sala sala;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lekar lekar;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Dijagnoza dijagnoza;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Recept recept;
	
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
	public TipPregleda getTip() {
		return tip;
	}
	public void setTip(TipPregleda tip) {
		this.tip = tip;
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
	public Lekar getLekar() {
		return lekar;
	}
	public void setLekar(Lekar lek) {
		this.lekar=lek;
		
	}
	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	
	
	public Recept getRecept() {
		return recept;
	}
	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	
	public Pregled() {
		super();
	}
	public Pregled(PregledDTO preg,StavkaCenovnika st,Lekar l,Sala s, TipPregleda t, Karton k) {
		this.datum=preg.getDatum();
		this.trajanje=preg.getTrajanje();
		this.cena=st;
		this.lekar=l;
		if (s==null) {
			this.tip=l.getTipPregleda();
		}else {
			this.sala=s;
			this.tip=t;
		}
		if (k!=null) {
			this.karton=k;
		}
	}
	@Override
	public int compareTo(Pregled o) {
		// TODO Auto-generated method stub
		return getDatum().compareTo(o.getDatum());
	}
}
