package rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rest.dto.AdministratorKCentraDTO;

@Entity
@Table(name="administratorKlinickogCentra")
public class AdministratorKlinickogCentra extends User{
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "KC", referencedColumnName = "id")
	@JsonIgnore
	private KlinickiCentar klinickiCentar;

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
		this.setBrojOsiguranika(dto.getBrojOsiguranika());
		this.setUloga(Uloga.ADMINISTRATOR_KLINICKOG_CENTRA);
		this.setPrviPut(dto.getPrviPut());
	}
}
