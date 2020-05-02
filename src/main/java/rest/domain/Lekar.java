package rest.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rest.dto.LekarDTO;

@Entity


@Table(name="lekar")
public class Lekar extends User{

	@OneToMany(mappedBy="lekar")
	private Set<Pregled> pregledi=new HashSet<Pregled>();

	
	@OneToMany(mappedBy="lekar")
	private Set<GodisnjiOdmor> godisnji= new HashSet<GodisnjiOdmor>();
	
	@ManyToOne
	@JsonIgnore
	private Klinika klinika;

	
	@Column(name="od")
	private Date RadnoVremeOd;
	@Column(name="do")
	private Date RadnoVremeDo;

	
	@ElementCollection
	@CollectionTable(name = "lekar_ocene",
    joinColumns = @JoinColumn(name="lekar_id",referencedColumnName = "id") )
	@Column(name = "ocene")
	private List<Integer> ocene = new ArrayList<Integer>();
	
	public Lekar(String username, String password, String ime, String prezime) {
		super(username, password, ime, prezime);
		// TODO Auto-generated constructor stub
	}



	public Lekar(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}



	public Lekar() {
	}

	public Lekar(LekarDTO lekarDTO) throws ParseException {
		// TODO Auto-generated constructor stub
		SimpleDateFormat df=new SimpleDateFormat("hh:mm");
		System.out.println("*********************************************");
		System.out.println(lekarDTO.getPassword());
		System.out.println(lekarDTO.getRadnoVremeDo());
		this.setIme(lekarDTO.getIme());
		this.setPrezime(lekarDTO.getPrezime());
		this.setUsername(lekarDTO.getUsername());
		this.setPassword(lekarDTO.getPassword());
		this.setRadnoVremeDo(df.parse(lekarDTO.getRadnoVremeDo()));
		this.setRadnoVremeOd(df.parse(lekarDTO.getRadnoVremeOd()));
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
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



	public void setKlinika(Klinika klinika2) {
		this.klinika = klinika2;		
	}



	public List<Integer> getOcene() {
		return ocene;
	}



	public void setOcene(List<Integer> ocene) {
		this.ocene = ocene;
	}
	
	
}
