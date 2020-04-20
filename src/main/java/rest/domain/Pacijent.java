package rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pacijent extends User{
	
	@Column
	private String brojOsiguranika;
	@Column
	private String adresa;
	@Column
	private String grad;
	@Column
	private String drzava;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "karton_id", referencedColumnName = "id")
	private Karton karton;
	
	public String getbrojOsiguranika() {
		return brojOsiguranika;
	}
	public void setbrojOsiguranika(String osiguranika) {
		brojOsiguranika = osiguranika;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public Karton getKarton() {
		return karton;
	}
	public void setKarton(Karton karton) {
		this.karton = karton;
	}
	
	public Pacijent() {
	}
	
	public Pacijent(String username, String password,String ime,String prezime, String osiguranika, String adresa, String grad, String drzava,
			Karton karton) {
		super(username, password,ime,prezime);
		this.brojOsiguranika = osiguranika;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.karton = karton;
	}
	
}
