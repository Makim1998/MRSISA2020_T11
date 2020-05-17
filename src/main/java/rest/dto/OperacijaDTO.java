package rest.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rest.domain.Lekar;
import rest.domain.Operacija;

public class OperacijaDTO implements Comparable<OperacijaDTO>{
	
	private Integer id;
	private Date datum;
	private String formatiran;
	private int trajanje;
	private KartonDTO karton;
	private StavkaCenovnikaDTO cena;
	private SalaDTO sala;
	private ArrayList<LekarDTO> lekari;
	
	public OperacijaDTO(Operacija s) {
		this.id=s.getId();
		this.datum=s.getDatum();
		String pattern = "dd.MM.yyyy. HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		this.formatiran = simpleDateFormat.format(s.getDatum());
		this.trajanje=s.getTrajanje();
		try {
			this.karton=new KartonDTO(s.getKarton());
		}catch (Exception e) {
			this.karton=null;
		}
		this.cena=new StavkaCenovnikaDTO(s.getCena());
		try {
			this.sala=new SalaDTO(s.getSala().getId(),s.getSala().getKlinika().getId(),s.getSala().getNaziv());
		}catch (Exception e) {
			this.sala=null;
		}
		this.lekari=new ArrayList<LekarDTO>();
		for (Lekar l : s.getLekari()) {
			new LekarDTO(l);
		}
	}

	public OperacijaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}


	public KartonDTO getKarton() {
		return karton;
	}

	public void setKarton(KartonDTO karton) {
		this.karton = karton;
	}

	public StavkaCenovnikaDTO getCena() {
		return cena;
	}

	public void setCena(StavkaCenovnikaDTO cena) {
		this.cena = cena;
	}

	public SalaDTO getSala() {
		return sala;
	}

	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}


	public String getFormatiran() {
		return formatiran;
	}

	public void setFormatiran(String formatiran) {
		this.formatiran = formatiran;
	}

	public ArrayList<LekarDTO> getLekari() {
		return lekari;
	}

	public void setLekari(ArrayList<LekarDTO> lekari) {
		this.lekari = lekari;
	}

	@Override
	public int compareTo(OperacijaDTO a) {
		// TODO Auto-generated method stub
		return getDatum().compareTo(a.getDatum());
	}
	
	

}

