package rest.domain;

import java.util.ArrayList;

import javax.persistence.OneToMany;

import javax.persistence.Entity;

@Entity
public class Lekar extends Medicinar{

	@OneToMany(mappedBy="lekar")
	private ArrayList<Pregled> pregledi;

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Lekar(String username, String password, ArrayList<GodisnjiOdmor> godisnji, Klinika klinika,
			ArrayList<Pregled> pregledi) {
		super(username, password, godisnji, klinika);
		this.pregledi = pregledi;
	}

	public Lekar() {
	}
	
}
