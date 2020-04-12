package rest.domain;

import java.util.ArrayList;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class AdministratorKlinickogCentra extends User{

	@OneToMany
	@JoinColumn(name="recepti")
	private ArrayList<Recept> recepti;
	@OneToMany
	@JoinColumn(name="administratori")
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
