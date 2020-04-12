package rest.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Entity;

@Entity
public class Medicinar extends User {
	
	@OneToMany(mappedBy="medicinar")
	private Set<GodisnjiOdmor> godisnji= new HashSet<GodisnjiOdmor>();
	@ManyToOne
	private Klinika klinika;
	
	
	public Klinika getKlinika() {
		return klinika;
	}
	
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Medicinar() {
	}

	public Medicinar(String username, String password, Set<GodisnjiOdmor> godisnji, Klinika klinika) {
		super(username, password);
		this.godisnji = godisnji;
		this.klinika = klinika;
	}
	
}
