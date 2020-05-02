package rest.dto;

import java.sql.Time;

import rest.domain.Klinika;
import rest.domain.Lekar;
	public class LekarDTO {
		private Integer id;
		private String ime;
		private String prezime;
		private String username;
		private String password;
		private String radnoVremeDo;
		private String radnoVremeOd;
		private String prosecnaOcena;
		private int kc_id;
		
		public void setProsek(Lekar l) {
			if(l.getOcene() == null) {
				prosecnaOcena = "nema";
			}
			else {
				double prosek = 0.;
				for(Integer i:l.getOcene()) {
					prosek += i;
				}
				prosek /= l.getOcene().size();
				prosecnaOcena = String.format("%.2f", prosek);
			}
		}
		public String getProsecnaOcena() {
			return prosecnaOcena;
		}
		public void setProsecnaOcena(String prosecnaOcena) {
			this.prosecnaOcena = prosecnaOcena;
		}
		public String getRadnoVremeDo() {
			return radnoVremeDo;
		}
		public void setRadnoVremeDo(String radnoVremeDo) {
			this.radnoVremeDo = radnoVremeDo;
		}
		public String getRadnoVremeOd() {
			return radnoVremeOd;
		}
		public void setRadnoVremeOd(String radnoVremeOd) {
			this.radnoVremeOd = radnoVremeOd;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getIme() {
			return ime;
		}
		public void setIme(String ime) {
			this.ime = ime;
		}
		public String getPrezime() {
			return prezime;
		}
		public void setPrezime(String prezime) {
			this.prezime = prezime;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getKc_id() {
			return kc_id;
		}
		public void setKc_id(int kc_id) {
			this.kc_id = kc_id;
		}
		public LekarDTO() {
			super();
		}
		public LekarDTO(Lekar s) {
			// TODO Auto-generated constructor stub
			this.setId(s.getId());
			this.setIme(s.getIme());
			this.setPrezime(s.getPrezime());
			this.setUsername(s.getUsername());
			this.setPassword(s.getPassword());
			this.setRadnoVremeDo(s.getRadnoVremeDo().toString().substring(11,16));
			
			this.setRadnoVremeOd(s.getRadnoVremeOd().toString().substring(11,16));
			this.setKc_id(s.getKlinika().getId());
		}
		public Klinika getKC() {
			// TODO Auto-generated method stub
			return null;
		}
}