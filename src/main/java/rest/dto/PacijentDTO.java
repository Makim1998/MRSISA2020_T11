package rest.dto;

import rest.domain.Pacijent;
import rest.domain.User;

public class PacijentDTO extends UserDTO {
	private String brojOsiguranika;
	private String adresa;
	private String grad;
	private String drzava;
	private KartonDTO karton;
	
	public String getBrojOsiguranika() {
		return brojOsiguranika;
	}
	public void setBrojOsiguranika(String brojOsiguranika) {
		this.brojOsiguranika = brojOsiguranika;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public KartonDTO getKarton() {
		return karton;
	}
	public void setKarton(KartonDTO karton) {
		this.karton = karton;
	}
	
	public PacijentDTO() {
		
	}
	public PacijentDTO(Pacijent p) {
		super((User) p);
		this.brojOsiguranika = p.getBrojOsiguranika();
		this.adresa = p.getAdresa();
		this.grad = p.getGrad();
		this.drzava = p.getDrzava();
		this.karton = new KartonDTO(p.getKarton());
	}
	
}
