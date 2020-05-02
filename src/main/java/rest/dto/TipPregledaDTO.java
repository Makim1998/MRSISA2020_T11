package rest.dto;

import java.util.StringTokenizer;

import rest.domain.TipPregleda;

public class TipPregledaDTO {

	public Integer id;
	public String naziv;
	public Integer klinika;
	

	public TipPregledaDTO() {
		super();
	}
	public TipPregledaDTO(Integer id, String naziv,Integer k) {
		this.id = id;
		this.naziv = naziv;
		this.klinika=k;
	}
	public TipPregledaDTO(String linija) {
		StringTokenizer st = new StringTokenizer(linija, "-");
		this.naziv = st.nextToken().trim();
		this.id = Integer.parseInt(st.nextToken().trim().substring(3));
	}

	public TipPregledaDTO(TipPregleda tipPregleda,Integer k) {
		this(tipPregleda.getId(),tipPregleda.getNaziv(),k);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public  String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Integer getKlinika() {
		return klinika;
	}
	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}
	
}
