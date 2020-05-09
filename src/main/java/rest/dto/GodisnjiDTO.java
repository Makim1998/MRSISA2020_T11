package rest.dto;

import java.util.Date;

import rest.domain.GodisnjiOdmor;

public class GodisnjiDTO {
	
	private Integer id;
	private Date datumPocetka;
	private Date datumKraja;
	private String razlog;
	private Boolean prihvacenOdbijen;
	private Integer medOsoblje_id;
	private UserDTO korisnik;
	
	
	public GodisnjiDTO() {
		super();
	}
	public GodisnjiDTO(Integer id, Date datumPocetka, Date datumKraja,String razlog, Boolean prihvacenOdbijen,
			Integer medOsoblje_id) {
		super();
		this.id = id;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.razlog=razlog;
		this.prihvacenOdbijen = prihvacenOdbijen;
		this.medOsoblje_id = medOsoblje_id;
		this.korisnik=null;
	}
	public GodisnjiDTO(GodisnjiOdmor s) {
		this.id=s.getId();
		this.datumPocetka=s.getDatumPocetka();
		this.datumKraja=s.getDatumKraja();
		this.razlog=null;
		this.prihvacenOdbijen=s.getPrihvacenOdbijen();
		this.medOsoblje_id=null;
		this.korisnik=new UserDTO(s.getMedOsoblje());
	}	
	
	public String getRazlog() {
		return razlog;
	}
	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}
	public UserDTO getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(UserDTO korisnik) {
		this.korisnik = korisnik;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatumPocetka() {
		return datumPocetka;
	}
	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public Date getDatumKraja() {
		return datumKraja;
	}
	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}
	public Boolean getPrihvacenOdbijen() {
		return prihvacenOdbijen;
	}
	public void setPrihvacenOdbijen(Boolean prihvacenOdbijen) {
		this.prihvacenOdbijen = prihvacenOdbijen;
	}
	public Integer getMedOsoblje_id() {
		return medOsoblje_id;
	}
	public void setMedOsoblje_id(Integer medOsoblje_id) {
		this.medOsoblje_id = medOsoblje_id;
	}
	
}
