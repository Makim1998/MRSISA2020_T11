package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Recept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany
	@JoinTable(name = "lecenje", joinColumns = @JoinColumn(name = "recept_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"))
	private Set<Lek> lekovi=new HashSet<Lek>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="sestra_id", referencedColumnName="id")
	private MedicinskaSestra sestra;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="dijagnoza_id", referencedColumnName="id", nullable=false)
	private Dijagnoza dijagnoza;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="admin_id", referencedColumnName="id")
	private AdministratorKlinickogCentra administrator;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="pregled_id", referencedColumnName="id", nullable=false)
	private Pregled pregled;
	
	public Recept() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Lek> getLekovi() {
		return lekovi;
	}
	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}
	public MedicinskaSestra getSestra() {
		return sestra;
	}
	public void setSestra(MedicinskaSestra sestra) {
		this.sestra = sestra;
	}
	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	public AdministratorKlinickogCentra getAdministrator() {
		return administrator;
	}
	public void setAdministrator(AdministratorKlinickogCentra administrator) {
		this.administrator = administrator;
	}
	public Pregled getPregled() {
		return pregled;
	}
	public void setPregled(Pregled pregled) {
		this.pregled = pregled;
	}
	

}
