package rest.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Pacijent extends User{
	
	@Column
	private String Osiguranika;
	@Column
	private String adresa;
	@Column
	private String grad;
	@Column
	private String drzava;
	@OneToOne
	@JoinColumn(name="karton")
	private Karton karton;
	
	public String getOsiguranika() {
		return Osiguranika;
	}
	public void setOsiguranika(String osiguranika) {
		Osiguranika = osiguranika;
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
	
	public Pacijent(String username, String password, String osiguranika, String adresa, String grad, String drzava,
			Karton karton) {
		super(username, password);
		Osiguranika = osiguranika;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.karton = karton;
	}
	
}
