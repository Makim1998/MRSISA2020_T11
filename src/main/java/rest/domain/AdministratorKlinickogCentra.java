package rest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rest.dto.AdministratorKCentraDTO;

@Entity
@Table(name="administratorKlinickogCentra")
public class AdministratorKlinickogCentra extends User{

	@OneToMany(mappedBy="administrator",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti=new HashSet<Recept>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "KC", referencedColumnName = "id")
	@JsonIgnore
	private KlinickiCentar klinickiCentar;
	
	public Set<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

	public KlinickiCentar getKlinickiCentar() {
		return klinickiCentar;
	}

	public void setKlinickiCentar(KlinickiCentar klinickiCentar) {
		this.klinickiCentar = klinickiCentar;
	}

	public AdministratorKlinickogCentra() {
	}
	
	public AdministratorKlinickogCentra(AdministratorKCentraDTO dto) {
		this.setAdresa(dto.getAdresa());
		this.setDrzava(dto.getDrzava());
		this.setUsername(dto.getUsername());
		this.setGrad(dto.getGrad());
		this.setId(dto.getId());
		this.setIme(dto.getIme());
		this.setPrezime(dto.getPrezime());
		this.setPassword(dto.getPassword());
		this.setUloga(Uloga.ADMINISTRATOR_KLINICKOG_CENTRA);
		this.setPrviPut(dto.getPrviPut());
		this.klinickiCentar = null;
	}
}
