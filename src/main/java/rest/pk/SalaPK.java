package rest.pk;

import java.io.Serializable;

public class SalaPK implements Serializable {
	private Integer brojSale;
	private Integer klinika;
	
	public SalaPK() {
		super();
	}
	public SalaPK(Integer brojSale, Integer klinikaID) {
		super();
		this.brojSale = brojSale;
		this.klinika = klinikaID;
	}
	@Override
	public int hashCode() {
		return brojSale.hashCode()+klinika.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof SalaPK) {
			SalaPK x=(SalaPK)o;
			return this.brojSale.equals(x.brojSale) && this.klinika.equals(x.klinika);
		}else
		return false;
	}
	public Integer getBrojSale() {
		return brojSale;
	}
	public void setBrojSale(Integer brojSale) {
		this.brojSale = brojSale;
	}
	public Integer getKlinika() {
		return klinika;
	}
	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

}
