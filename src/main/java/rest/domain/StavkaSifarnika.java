package rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import rest.dto.StavkaSifarnikaDTO;

@Entity
public class StavkaSifarnika {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String sifra;

	@Column(nullable=false)
	private Integer stavkaId;
	
	@Column(nullable=false)
	private TipSifre tip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public Integer getStavkaId() {
		return stavkaId;
	}

	public void setStavkaId(Integer stavkaId) {
		this.stavkaId = stavkaId;
	}

	public TipSifre getTip() {
		return tip;
	}

	public void setTip(TipSifre tip) {
		this.tip = tip;
	}
	
	public void setTip(String tip) {
		if (tip.equals("DIJAGNOZA"))
			this.tip = TipSifre.DIJAGNOZA;
		else
			this.tip = TipSifre.LEK;
	}

	public StavkaSifarnika(Integer id, String sifra, Integer stavkaId, TipSifre tip) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.stavkaId = stavkaId;
		this.tip = tip;
	}

	public StavkaSifarnika() {
	}
	
	public StavkaSifarnika(StavkaSifarnikaDTO dto) {
		this.id = dto.getId();
		this.sifra = dto.getSifra();
		this.stavkaId = dto.getStavkaId();
		this.setTip(dto.getTip());
	}
}
