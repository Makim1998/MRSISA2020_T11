package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Lek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "sifra", unique = true, nullable = false)
	private String sifra;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@ManyToMany(mappedBy="lekovi")
	private Set<Recept> recepti=new HashSet<Recept>();
	
	public Lek() {
		super();
	}
	public Set<Recept> getRecepti() {
		return recepti;
	}
	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}
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
}
