package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Lekar extends Medicinar{

	@OneToMany(mappedBy="lekar")
	private Set<Pregled> pregledi=new HashSet<Pregled>();


	public Lekar(String username, String password, Set<GodisnjiOdmor> godisnji, Klinika klinika,
			Set<Pregled> pregledi) {
		super(username, password, godisnji, klinika);
		this.pregledi = pregledi;
	}

	public Lekar() {
	}
	
}
