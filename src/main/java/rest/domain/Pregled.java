package rest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity 
public class Pregled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date datum;
	
	@Column
	private int trajanje;
	
	@ManyToOne
	private TipPregleda tip;
	
	@ManyToOne
	private Karton karton;
	
	@OneToOne
	private StavkaCenovnika cena;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Lekar lekar;
	
	@ManyToOne
	private Dijagnoza dijagnoza;
	
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
	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}
	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	public Pregled() {
		super();
	}
}
