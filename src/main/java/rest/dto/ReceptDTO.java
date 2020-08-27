package rest.dto;

import rest.domain.Recept;

public class ReceptDTO {
	
	private Integer id;
	private String sestra;
	private String dijagnoza;
	private String administrator;
	private String lekar;
	private String lekovi;	
	
	public ReceptDTO(Recept recept) {
		this.id = recept.getId();
		this.dijagnoza = recept.getDijagnoza().getOpis();
		this.administrator = recept.getAdministrator().getEmail();
		this.lekovi = "";
		this.lekar = recept.getDijagnoza().getLekar().getIme() + " " + recept.getDijagnoza().getLekar().getPrezime();
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
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public String getLekovi() {
		return lekovi;
	}

	public void setLekovi(String lekovi) {
		this.lekovi = lekovi;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

}
