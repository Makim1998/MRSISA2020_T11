package rest.domain;

import java.util.ArrayList;

public class Medicinar extends User {
	
	private ArrayList<GodisnjiOdmor> godisnji;
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
