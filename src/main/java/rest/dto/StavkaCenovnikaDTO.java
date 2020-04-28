package rest.dto;


import java.util.StringTokenizer;

import rest.domain.StavkaCenovnika;

public class StavkaCenovnikaDTO {
	
	private Integer id;
	private Integer c_id;
	private int cena;
	private String usluga;
	
	public StavkaCenovnikaDTO(String linija) {
		StringTokenizer st = new StringTokenizer(linija, "-");
		this.usluga= st.nextToken().trim();
		String cena= st.nextToken().trim();
		this.cena=Integer.parseInt(cena.substring(0,cena.length()-3));
		this.id = Integer.parseInt(st.nextToken().trim().substring(3));
	}
	
	public StavkaCenovnikaDTO(StavkaCenovnika next) {
		this.c_id=next.getCenovnik().getId();
		this.id=next.getId();
		this.cena=next.getCena();
		this.usluga=next.getUsluga();
	}
	
	
	public StavkaCenovnikaDTO() {
		super();
	}

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getUsluga() {
		return usluga;
	}
	public void setUsluga(String usluga) {
		this.usluga = usluga;
	}
}
