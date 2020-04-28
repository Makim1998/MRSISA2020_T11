package rest.dto;

import java.util.StringTokenizer;

import rest.domain.Sala;

public class SalaDTO {
	private Integer id;
	public String naziv;
	public Integer getId() {
		return id;
	}
	public SalaDTO(String linija) {
		StringTokenizer st = new StringTokenizer(linija, "-");
		this.naziv = st.nextToken().trim();
		this.id = Integer.parseInt(st.nextToken().trim().substring(3));
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
