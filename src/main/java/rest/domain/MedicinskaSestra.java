package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class MedicinskaSestra extends User {
	
	@OneToMany(mappedBy="sestra")
	private Set<Recept> recepti=new HashSet<Recept>();

	@OneToMany(mappedBy="sestra")
	private Set<GodisnjiOdmor> godisnji= new HashSet<GodisnjiOdmor>();
	
	@ManyToOne
	private Klinika klinika;

	

	public MedicinskaSestra(String username, String password, String ime, String prezime) {
		super(username, password, ime, prezime);
		// TODO Auto-generated constructor stub
	}




	public MedicinskaSestra(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}




	public MedicinskaSestra() {
	}
	
}
