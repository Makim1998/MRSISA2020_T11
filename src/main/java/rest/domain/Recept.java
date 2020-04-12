package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Recept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToMany
	private Set<Lek> lekovi=new HashSet<Lek>();
	@ManyToOne
	private MedicinskaSestra sestra;
	@ManyToOne
	private Dijagnoza dijagnoza;
	@ManyToOne
	private AdministratorKlinickogCentra administrator;
	
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

}
