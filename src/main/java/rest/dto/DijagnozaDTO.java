package rest.dto;

import rest.domain.Dijagnoza;

public class DijagnozaDTO {
	
	private Integer id;

	public DijagnozaDTO(Dijagnoza dijagnoza) {
		// TODO Auto-generated constructor stub
		this.id=dijagnoza.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
