package rest.dto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rest.domain.Cenovnik;
import rest.domain.Klinika;
import rest.domain.StavkaCenovnika;

public class CenovnikDTO {

	private Integer id;
	private Set<StavkaCenovnikaDTO> stavke = new HashSet<StavkaCenovnikaDTO>();
	private Integer klinikaID;
	
	public CenovnikDTO(Cenovnik cenovnik) {
		this.setId(cenovnik.getId());
		this.setStavke(cenovnik.getStavke());
		this.setKlinikaID(cenovnik.getKlinika().getId());

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<StavkaCenovnikaDTO> getStavke() {
		return stavke;
	}

	public void setStavke(Set<StavkaCenovnika> stavko) {
		Iterator<StavkaCenovnika> itr = stavko.iterator();
		while(itr.hasNext()){
			StavkaCenovnikaDTO s=new StavkaCenovnikaDTO(itr.next());
			this.stavke.add(s);
			}
	}

	public Integer getKlinikaID() {
		return klinikaID;
	}

	public void setKlinikaID(Integer id) {
		this.klinikaID=id;
	}

}
