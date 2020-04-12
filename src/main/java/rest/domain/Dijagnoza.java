package rest.domain;

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

@Entity
public class Dijagnoza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "opis")
	private String opis;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recept_id", referencedColumnName = "id")
	private Recept recept;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Lekar lekar;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pregled_id", referencedColumnName = "id")
	private Pregled pregled;
	
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
	
	public Recept getRecept() {
		return recept;
	}
	public void setRecept(Recept recept) {
		this.recept = recept;
	}
	public Lekar getLekar() {
		return lekar;
	}
	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}
	public Pregled getPregled() {
		return pregled;
	}
	public void setPregled(Pregled pregled) {
		this.pregled = pregled;
	}


}
