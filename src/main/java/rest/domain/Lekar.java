package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lekar  extends User{
	@OneToMany(mappedBy="lekar")
	private Set<Pregled> pregledi=new HashSet<Pregled>();
	
	@OneToMany(mappedBy="lekar")
	private Set<GodisnjiOdmor> godisnji= new HashSet<GodisnjiOdmor>();
	
	@ManyToOne
	private Klinika klinika;

	

	public Lekar(String username, String password, String ime, String prezime) {
		super(username, password, ime, prezime);
		// TODO Auto-generated constructor stub
	}



	public Lekar(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}



	public Lekar() {
	}
	
}
