package rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="administratorKlinike")
public class AdministratorKlinike extends User{
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
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
