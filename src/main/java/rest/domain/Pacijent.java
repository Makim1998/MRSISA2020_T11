package rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pacijent extends User{
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "karton_id", referencedColumnName = "id")
	private Karton karton;
	
	@Column(name="odobren")
	private Boolean odobren = Boolean.TRUE;
	
	public Karton getKarton() {
		return karton;
	}
	public void setKarton(Karton karton) {
		this.karton = karton;
	}
	
	public Pacijent() {
	}
	
	public Pacijent(String username, String password,String ime,String prezime, String osiguranika, String adresa, String grad, String drzava,
			Karton karton, Uloga uloga) {
		super(username, password,ime,prezime,osiguranika,adresa,grad,drzava,uloga);
		this.karton = karton;
	}
	
	public Boolean getOdobren() {
		return odobren;
	}
	public void setOdobren(Boolean odobren) {
		this.odobren = odobren;
	}
		
}
