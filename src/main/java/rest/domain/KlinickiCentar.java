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
import javax.persistence.OneToMany;

@Entity
public class KlinickiCentar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String naziv;
	
	@OneToMany(mappedBy="klinickiCentar",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorKlinickogCentra> admini = new HashSet<AdministratorKlinickogCentra>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Klinika> getKlinike() {
		return klinike;
	}

	public void setKlinike(Set<Klinika> klinike) {
		this.klinike = klinike;
	}

	@OneToMany(mappedBy="klinickiCentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Klinika> klinike = new HashSet<Klinika>();

	public KlinickiCentar(Integer id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public KlinickiCentar() {
	}

	public Set<AdministratorKlinickogCentra> getAdmini() {
		return admini;
	}

	public void setAdmini(Set<AdministratorKlinickogCentra> admini) {
		this.admini = admini;
	}
	
}

