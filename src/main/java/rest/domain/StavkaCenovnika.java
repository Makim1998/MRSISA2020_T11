package rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import rest.dto.StavkaCenovnikaDTO;
import rest.service.CenovnikService;

@Entity
public class StavkaCenovnika {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private int cena;
	@Column (nullable = false)
	private String usluga;
	@ManyToOne
	private Cenovnik cenovnik;
	
	
	public Cenovnik getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}
	public String getUsluga() {
		return usluga;
	}
	public void setUsluga(String usluga) {
		this.usluga = usluga;
	}
	public StavkaCenovnika() {
		super();
	}
	public StavkaCenovnika(StavkaCenovnikaDTO stavkaCenovnikaDTO,Cenovnik cenovnik) {
		this.id=stavkaCenovnikaDTO.getId();
		this.cena=stavkaCenovnikaDTO.getCena();
		this.usluga=stavkaCenovnikaDTO.getUsluga();
		this.cenovnik=cenovnik;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
}
