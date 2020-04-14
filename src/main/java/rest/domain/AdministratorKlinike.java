package rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class AdministratorKlinike extends User{
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Klinika klinika;
	
	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	


	public AdministratorKlinike(String username, String password, Klinika klinika) {
		super(username, password);
		this.klinika = klinika;
	}
	
	public AdministratorKlinike() {
	}
}
