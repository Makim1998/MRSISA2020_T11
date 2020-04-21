package rest.dto;

import rest.domain.Uloga;
import rest.domain.User;

public class UserDTO {
	private Integer id;
	private String email;
    private String password;
    private String ime;
    private String prezime;
	private Uloga uloga;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
	public UserDTO() {
	}
	public UserDTO(User u) {
		this.email = u.getUsername();
		this.password = u.getPassword();
		this.ime = u.getIme();
		this.prezime = u.getPrezime();
		this.id = u.getId();
		this.uloga = u.getUloga();
	}
	
	public UserDTO(String email) {
		super();
		this.email = email;
	}
}
