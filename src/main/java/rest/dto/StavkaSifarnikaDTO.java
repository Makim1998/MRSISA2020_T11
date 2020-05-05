package rest.dto;

import rest.domain.StavkaSifarnika;

public class StavkaSifarnikaDTO {
	
	private Integer id;
	private String sifra;
	private Integer stavkaId;
	private String tip;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public Integer getStavkaId() {
		return stavkaId;
	}
	public void setStavkaId(Integer stavkaId) {
		this.stavkaId = stavkaId;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public StavkaSifarnikaDTO(StavkaSifarnika stavka) {
		this.id = stavka.getId();
		this.sifra = stavka.getSifra();
		this.stavkaId = stavka.getStavkaId();
		this.tip = stavka.getTip().name();
	}
	
	public StavkaSifarnikaDTO() {
	}	
	
}
