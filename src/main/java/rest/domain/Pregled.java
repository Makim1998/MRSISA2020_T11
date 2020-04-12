package rest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name="tip",referencedColumnName = "naziv" ,nullable=false)
	private TipPregleda tip;
	@ManyToOne
	@JoinColumn(name="karton", nullable=false)
	private Karton karton;
	@OneToOne
	@JoinColumn(name="cena", nullable=false)
	private StavkaCenovnika cena;
	@ManyToOne
	@JoinColumn(name="sala", nullable=false)
	private Sala sala;
	@ManyToOne
	@JoinColumn(name="lekar", nullable=false)
	private Lekar lekar;
	@ManyToOne
	@JoinColumn(name="dijagnoza", nullable=false)
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
