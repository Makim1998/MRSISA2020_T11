package rest.domain;

import javax.persistence.Column;
import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;


@Entity
@Inheritance(strategy=JOINED)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq")
	private int id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column
    private String password;
	@Column
    private String ime;
	@Column
    private String prezime;
	
	@Column
	private Uloga uloga;
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public User() {
	}
	
	public User(String username, String password) {
		super();
		this.email = username;
		this.password = password;
	}

	
	public User(String username, String password, String ime, String prezime) {
		super();
		this.email = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getUsername() {
		return email;
	}
	public void setUsername(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	

}
