package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class AdministratorKlinickogCentra extends User{

	@OneToMany(mappedBy="AdministratorKlinickogCentra",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti=new HashSet<Recept>();
	
	@OneToMany(mappedBy="AdministratorKlinickogCentra",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorKlinike> administratori = new HashSet<AdministratorKlinike>();
	

	
	public AdministratorKlinickogCentra(String username, String password, Set<Recept> recepti,
			Set<AdministratorKlinike> administratori) {
		super(username, password);
		this.recepti = recepti;
		this.administratori = administratori;
	}
	
	public AdministratorKlinickogCentra() {
	}
	
}
