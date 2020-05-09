package rest.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rest.dto.MedicinskaSestraDTO;

@Entity
@Table(name="medicinskaSestra")
public class MedicinskaSestra extends User {
	
	
	@OneToMany(mappedBy="sestra",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti=new HashSet<Recept>();

	@OneToMany(mappedBy="medOsoblje",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<GodisnjiOdmor> godisnji= new HashSet<GodisnjiOdmor>();
	
	@Column(name="od")
	private Date RadnoVremeOd;
	@Column(name="do")
	private Date RadnoVremeDo;
	
	@ManyToOne
	@JoinColumn(name="klinika",nullable=false)
	@JsonIgnore
	private Klinika klinika;

	

	public MedicinskaSestra(String username, String password, String ime, String prezime) {
		super(username, password, ime, prezime);
		// TODO Auto-generated constructor stub
	}




	public MedicinskaSestra(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}




	public MedicinskaSestra() {
	}




	public Set<Recept> getRecepti() {
		return recepti;
	}




	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}




	public Set<GodisnjiOdmor> getGodisnji() {
		return godisnji;
	}




	public void setGodisnji(Set<GodisnjiOdmor> godisnji) {
		this.godisnji = godisnji;
	}




	public Date getRadnoVremeOd() {
		return RadnoVremeOd;
	}




	public void setRadnoVremeOd(Date radnoVremeOd) {
		RadnoVremeOd = radnoVremeOd;
	}




	public Date getRadnoVremeDo() {
		return RadnoVremeDo;
	}




	public void setRadnoVremeDo(Date radnoVremeDo) {
		RadnoVremeDo = radnoVremeDo;
	}




	public Klinika getKlinika() {
		return klinika;
	}




	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	public MedicinskaSestra(MedicinskaSestraDTO dto) throws ParseException {
		SimpleDateFormat df=new SimpleDateFormat("hh:mm");
		this.setAdresa(dto.getAdresa());
		this.setBrojOsiguranika(dto.getBrojOsiguranika());
		this.setDrzava(dto.getDrzava());
		this.setEmail(dto.getUsername());
		this.setGrad(dto.getGrad());
		this.setId(dto.getId());
		this.setIme(dto.getIme());
		this.setPassword(dto.getPassword());
		this.setPrezime(dto.getPrezime());
		this.setPrviPut(dto.getPrviPut());
		this.setRadnoVremeDo(df.parse(dto.getRadnoVremeDo()));
		this.setRadnoVremeOd(df.parse(dto.getRadnoVremeOd()));
		this.setUloga(Uloga.MEDICINSKA_SESTRA);
		this.setKlinika(null);
	}
}
