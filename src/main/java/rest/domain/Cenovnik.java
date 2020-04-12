package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cenovnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy="cenovnik")
	private Set<StavkaCenovnika> stavka=new HashSet<StavkaCenovnika>();
	@OneToOne
	private Klinika klinika;
	public Cenovnik() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<StavkaCenovnika> getStavka() {
		return stavka;
	}
	public void setStavka(Set<StavkaCenovnika> stavka) {
		this.stavka = stavka;
	}
	public Klinika getKlinika() {
		return klinika;
	}
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
}
