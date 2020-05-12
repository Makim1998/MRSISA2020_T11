package rest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import rest.domain.Pregled;

public class PregledDTO {
	
	private Integer id;
	private Date datum;
	private String formatiran;
	private int trajanje;
	private TipPregledaDTO tip;
	private KartonDTO karton;
	private StavkaCenovnikaDTO cena;
	private SalaDTO sala;
	private LekarDTO lekar;
	private DijagnozaDTO dijagnoza;
	
	public PregledDTO(Pregled s) {
		this.id=s.getId();
		this.datum=s.getDatum();
		String pattern = "dd.MM.yyyy. HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		this.formatiran = simpleDateFormat.format(s.getDatum());
		this.trajanje=s.getTrajanje();
		this.tip=new TipPregledaDTO(s.getTip(),s.getSala().getKlinika().getId());
		try {
			this.karton=new KartonDTO(s.getKarton());
		}catch (Exception e) {
			this.karton=null;
		}
		this.cena=new StavkaCenovnikaDTO(s.getCena());
		this.sala=new SalaDTO(s.getSala().getId(),s.getSala().getKlinika().getId(),s.getSala().getNaziv());
		this.lekar=new LekarDTO(s.getLekar());
		try {
			this.dijagnoza=new DijagnozaDTO(s.getDijagnoza());
		}catch (Exception e) {
			this.dijagnoza=null;
		}
	}

	public PregledDTO() {
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

	public TipPregledaDTO getTip() {
		return tip;
	}

	public void setTip(TipPregledaDTO tip) {
		this.tip = tip;
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

	public LekarDTO getLekar() {
		return lekar;
	}

	public void setLekar(LekarDTO lekar) {
		this.lekar = lekar;
	}

	public DijagnozaDTO getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(DijagnozaDTO dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public String getFormatiran() {
		return formatiran;
	}

	public void setFormatiran(String formatiran) {
		this.formatiran = formatiran;
	}
	
	

}
