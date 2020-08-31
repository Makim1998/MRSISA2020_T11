package rest.dto;

import rest.domain.Recept;

public class ReceptDTO {
	
	private Integer id;
	private String sestra;
	private String dijagnoza;
	private String pacijent;
	private String lekovi;	
	
	public ReceptDTO(Recept recept) {
		this.id = recept.getId();
		this.dijagnoza = recept.getDijagnoza().getOpis();
		this.lekovi = "";
		this.pacijent = recept.getPregled().getKarton().getIme() + " " + recept.getPregled().getKarton().getPrezime();
	}
	
	public ReceptDTO() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSestra() {
		return sestra;
	}
	public void setSestra(String sestra) {
		this.sestra = sestra;
	}
	public String getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public String getLekovi() {
		return lekovi;
	}

	public void setLekovi(String lekovi) {
		this.lekovi = lekovi;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

}
