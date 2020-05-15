package rest.dto;

import rest.domain.AdministratorKlinike;

public class AdministratorKlinikeDTO {
	private Integer id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String adresa;
	private String grad;
	private String drzava;
	private Integer kc_id;
	private Boolean prviPut;
	public AdministratorKlinikeDTO() {
		super();
	}
	public AdministratorKlinikeDTO(AdministratorKlinike s) {
		// TODO Auto-generated constructor stub
		this.setId(s.getId());
		this.setIme(s.getIme());
		this.setPrezime(s.getPrezime());
		this.setUsername(s.getUsername());
		this.setPassword(s.getPassword());
		this.setAdresa(s.getAdresa());
		this.setDrzava(s.getDrzava());
		this.setGrad(s.getGrad());
		System.out.println("s.getKlinikaid "+s.getKlinika().getId());
		this.setKc_id(s.getKlinika().getId());
		this.setPrviPut(s.getPrviPut());
	}


	public Boolean getPrviPut() {
		return prviPut;
	}
	public void setPrviPut(Boolean prviPut) {
		this.prviPut = prviPut;
	}
	public void setKc_id(Integer kc_id) {
		this.kc_id = kc_id;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getKc_id() {
		return kc_id;
	}

	public void setKc_id(int kc_id) {
		this.kc_id = kc_id;
	}

}
