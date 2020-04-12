package rest.domain;

import java.util.ArrayList;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Medicinar extends User {
	
	@OneToMany
	@JoinColumn(name="godisnji")
	private ArrayList<GodisnjiOdmor> godisnji;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	
	public ArrayList<GodisnjiOdmor> getGodisnji() {
		return godisnji;
	}
	
	public void setGodisnji(ArrayList<GodisnjiOdmor> godisnji) {
		this.godisnji = godisnji;
	}
	
	public Klinika getKlinika() {
		return klinika;
	}
	
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Medicinar() {
	}

	public Medicinar(String username, String password, ArrayList<GodisnjiOdmor> godisnji, Klinika klinika) {
		super(username, password);
		this.godisnji = godisnji;
		this.klinika = klinika;
	}
	
}
