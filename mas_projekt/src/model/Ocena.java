package model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Ocena")
public class Ocena {

	public long id;
	public LocalDate dataWystawienia;
	public int ocena;
	public String komentarz;
	public Uczen uczen;
	
	
	
	public Ocena() {
	}

	public Ocena(LocalDate dataWystawienia, int ocena, String komentarz, Uczen uczen) {
		super();
		this.dataWystawienia = dataWystawienia;
		this.ocena = ocena;
		this.komentarz = komentarz;
		this.uczen = uczen;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy  = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	private long getId() {
		return id;
	}



	private void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	private Uczen getUczen() {
		return uczen;
	}

	private void setUczen(Uczen uczen) {
		this.uczen = uczen;
	}

	@Basic
	private LocalDate getDataWystawienia() {
		return dataWystawienia;
	}

	private void setDataWystawienia(LocalDate dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}
	
	@Basic
	private int getOcena() {
		return ocena;
	}

	private void setOcena(int ocena) {
		this.ocena = ocena;
	}

	@Basic
	private String getKomentarz() {
		return komentarz;
	}

	private void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}

	@Override
	public String toString() {
		return "Ocena " + ocena + ", komentarz=" + komentarz;
	}
	
	
	
	
}
