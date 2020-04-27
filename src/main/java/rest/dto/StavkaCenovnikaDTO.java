package rest.dto;



import rest.domain.Cenovnik;
import rest.domain.StavkaCenovnika;

public class StavkaCenovnikaDTO {

	private Integer id;
	private int cena;
	private String usluga;
	public StavkaCenovnikaDTO(StavkaCenovnika next) {
		this.id=next.getId();
		this.cena=next.getCena();
		this.usluga=next.getUsluga();
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
