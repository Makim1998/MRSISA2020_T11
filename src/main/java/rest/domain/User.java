package rest.domain;

import javax.persistence.Column;
import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import org.hibernate.validator.constraints.Length;


@Entity
@Inheritance(strategy=JOINED)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false,length = 10)
    private String password;
	@Column(nullable = false,length = 15)
    private String ime;
	@Column(nullable = false,length = 15)
    private String prezime;
	@Column(nullable = false)
    private Boolean prviPut;
	
	@Column(nullable = false)
	@Length(min = 13, max = 13)
	private String brojOsiguranika;
	
	@Column
	private String adresa;
	@Column
	private String grad;
	@Column
	private String drzava;
	@Column
	private Uloga uloga;
	
    
	public User(String email, String password, String ime, String prezime, String brojOsiguranika,
			String adresa, String grad, String drzava, Uloga uloga) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.brojOsiguranika = brojOsiguranika;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.uloga = uloga;
	}

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
	public Boolean getPrviPut() {
		return prviPut;
	}

	public void setPrviPut(Boolean prviPut) {
		this.prviPut = prviPut;
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
	

}
