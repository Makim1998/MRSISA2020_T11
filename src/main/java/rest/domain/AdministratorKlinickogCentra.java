package rest.domain;

import java.util.ArrayList;

import javax.persistence.OneToMany;

public class AdministratorKlinickogCentra extends User{

	@OneToMany(mappedBy="administrator")
	private ArrayList<Recept> recepti;
	@OneToMany(mappedBy="admin")
	private ArrayList<AdministratorKlinike> administratori;
	
	
	public ArrayList<AdministratorKlinike> getAdministratori() {
		return administratori;
	}
	public void setAdministratori(ArrayList<AdministratorKlinike> administratori) {
		this.administratori = administratori;
	}
	public ArrayList<Recept> getRecepti() {
		return recepti;
	}
	public void setRecepti(ArrayList<Recept> recepti) {
		this.recepti = recepti;
	}
	
	public AdministratorKlinickogCentra(String username, String password, ArrayList<Recept> recepti,
			ArrayList<AdministratorKlinike> administratori) {
		super(username, password);
		this.recepti = recepti;
		this.administratori = administratori;
	}
	
	public AdministratorKlinickogCentra() {
	}
	
}
