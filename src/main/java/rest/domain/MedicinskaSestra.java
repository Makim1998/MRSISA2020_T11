package rest.domain;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class MedicinskaSestra extends Medicinar {
	
	private ArrayList<Recept> recepti;

	public ArrayList<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(ArrayList<Recept> recepti) {
		this.recepti = recepti;
	}

	public MedicinskaSestra(String username, String password, ArrayList<GodisnjiOdmor> godisnji, Klinika klinika,
			ArrayList<Recept> recepti) {
		super(username, password, godisnji, klinika);
		this.recepti = recepti;
	}

	public MedicinskaSestra() {
	}
	
}
