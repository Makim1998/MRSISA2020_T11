package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cenovnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="cenovnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StavkaCenovnika> stavke = new HashSet<StavkaCenovnika>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "klinika_id", referencedColumnName = "id")
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
		return stavke;
	}
	public void setStavka(Set<StavkaCenovnika> stavka) {
		this.stavke = stavka;
	}
	public Klinika getKlinika() {
		return klinika;
	}
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
}
