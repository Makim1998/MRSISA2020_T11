package rest.dto;

import rest.domain.Sala;

public class SalaDTO {
	private Integer id;
	public String naziv;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public SalaDTO() {
		super();
	}
	public SalaDTO(Sala s) {
		this.id=s.getId();
		this.naziv=s.getNaziv();
	}
}
