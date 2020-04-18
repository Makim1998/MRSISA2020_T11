package rest.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GodisnjiOdmor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "datumPocetka", nullable = false)
	private Date datumPocetka;
	
	@Column(name = "datumKraja", nullable = false)
	private Date datumKraja;
	
	@Column(name = "prihvacenOdbijen")
	private Boolean prihvacenOdbijen;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Medicinar medicinar;
	
	public GodisnjiOdmor() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatumPocetka() {
		return datumPocetka;
	}
	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public Date getDatumKraja() {
		return datumKraja;
	}
	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}
	public Boolean getPrihvacenOdbijen() {
		return prihvacenOdbijen;
	}
	public void setPrihvacenOdbijen(Boolean prihvacenOdbijen) {
		this.prihvacenOdbijen = prihvacenOdbijen;
	}
	public Medicinar getMedicinar() {
		return medicinar;
	}
	public void setMedicinar(Medicinar medicinar) {
		this.medicinar = medicinar;
	}


}
