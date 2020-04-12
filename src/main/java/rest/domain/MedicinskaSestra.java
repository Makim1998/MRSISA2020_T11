package rest.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import javax.persistence.Entity;

@Entity
public class MedicinskaSestra extends Medicinar {
	
	@OneToMany(mappedBy="sestra")
	private Set<Recept> recepti=new HashSet<Recept>();


	public MedicinskaSestra(String username, String password, Set<GodisnjiOdmor> godisnji, Klinika klinika,
			Set<Recept> recepti) {
		super(username, password, godisnji, klinika);
		this.recepti = recepti;
	}

	public MedicinskaSestra() {
	}
	
}
