package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Dijagnoza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String opis;
	@OneToMany(mappedBy="dijagnoza")
	private Set<Recept> recepti =new HashSet<Recept>();
	@ManyToOne
	private Lekar lekar;
	@OneToMany(mappedBy="dijagnoza")
	private Set<Pregled> pregledi =new HashSet<Pregled>();
	
	public Dijagnoza() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Set<Recept> getRecepti() {
		return recepti;
	}
	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}
	public Lekar getLekar() {
		return lekar;
	}
	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}
	public Set<Pregled> getPregledi() {
		return pregledi;
	}
	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

}
