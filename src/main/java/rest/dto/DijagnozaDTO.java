package rest.dto;

import rest.domain.Dijagnoza;

public class DijagnozaDTO {
	
	public DijagnozaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer id;
	
	private String opis;

	public DijagnozaDTO(Dijagnoza dijagnoza) {
		// TODO Auto-generated constructor stub
		this.id=dijagnoza.getId();
		this.opis=dijagnoza.getOpis();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	
}
