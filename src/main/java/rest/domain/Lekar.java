package rest.domain;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rest.dto.LekarDTO;

@Entity
@Table(name="lekar")
public class Lekar extends Medicinar{

	@OneToMany(mappedBy="lekar")
	private Set<Pregled> pregledi=new HashSet<Pregled>();
	@Column(name="od")
	private Date RadnoVremeOd;
	@Column(name="do")
	private Date RadnoVremeDo;

	public Lekar(String username, String password, Set<GodisnjiOdmor> godisnji, Klinika klinika,
			Set<Pregled> pregledi) {
		super(username, password, godisnji, klinika);
		this.pregledi = pregledi;
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
	
}
