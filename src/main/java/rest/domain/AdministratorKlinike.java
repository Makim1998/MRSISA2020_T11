package rest.domain;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class AdministratorKlinike extends User{
	
	@OneToOne
	private Klinika klinika;
	@ManyToOne
	private AdministratorKlinickogCentra admin;

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	public AdministratorKlinickogCentra getAdmin() {
		return admin;
	}

	public void setAdmin(AdministratorKlinickogCentra admin) {
		this.admin = admin;
	}

	public AdministratorKlinike(String username, String password, Klinika klinika) {
		super(username, password);
		this.klinika = klinika;
	}
	
	public AdministratorKlinike() {
	}
}
