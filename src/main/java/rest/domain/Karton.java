package rest.domain;

import java.util.Date;
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
import javax.persistence.OneToOne;

@Entity
public class Karton {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "datumRodjenja",nullable = false)
	private Date datumRodjenja;
	
	@Column(name = "krvnaGrupa",nullable = false)
	private String krvnaGrupa;
	
	@OneToOne(mappedBy = "karton")
	private Pacijent pacijent;
	
	@OneToMany(mappedBy="karton", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi =new HashSet<Pregled>();
	
	public Set<Pregled> getPregledi() {
		return pregledi;
	}
	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
	public Karton() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}
	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}
	public Pacijent getPacijent() {
		return pacijent;
	}
	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
}
