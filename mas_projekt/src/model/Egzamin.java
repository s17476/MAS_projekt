package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Egzamin")
public class Egzamin {

	public long id;
	public String tytul;
	public List<PytanieEgzaminacyjne> pytaniaEgzaminacyjne;
	public int iloscPunktow;
	public int dostepnyCzas;
	public LocalDate dostepnyOd;
	public LocalDate dostepnyDo;
	public Ocena ocena;
	public Przedmiot przedmiot;
	

	public Egzamin() {
	}

	public Egzamin(String tytul, List<PytanieEgzaminacyjne> pytaniaEgzaminacyjne, int iloscPunktow, int dostepnyCzas,
			LocalDate dostepnyOd, LocalDate dostepnyDo, Przedmiot przedmiot) {
		super();
		this.tytul = tytul;
		this.pytaniaEgzaminacyjne = pytaniaEgzaminacyjne;
		this.iloscPunktow = iloscPunktow;
		this.dostepnyCzas = dostepnyCzas;
		this.dostepnyOd = dostepnyOd;
		this.dostepnyDo = dostepnyDo;
		this.przedmiot = przedmiot;
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Przedmiot getPrzedmiot() {
		return przedmiot;
	}

	public void setPrzedmiot(Przedmiot przedmiot) {
		this.przedmiot = przedmiot;
	}

	@Basic
	private String getTytul() {
		return tytul;
	}

	private void setTytul(String tytul) {
		this.tytul = tytul;
	}
	@Basic
	private int getIloscPunktow() {
		return iloscPunktow;
	}

	private void setIloscPunktow(int iloscPunktow) {
		this.iloscPunktow = iloscPunktow;
	}
	@Basic
	private int getDostepnyCzas() {
		return dostepnyCzas;
	}

	private void setDostepnyCzas(int dostepnyCzas) {
		this.dostepnyCzas = dostepnyCzas;
	}
	@Basic
	private LocalDate getDostepnyOd() {
		return dostepnyOd;
	}

	private void setDostepnyOd(LocalDate dostepnyOd) {
		this.dostepnyOd = dostepnyOd;
	}
	@Basic
	private LocalDate getDostepnyDo() {
		return dostepnyDo;
	}

	private void setDostepnyDo(LocalDate dostepnyDo) {
		this.dostepnyDo = dostepnyDo;
	}
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<PytanieEgzaminacyjne> getPytaniaEgzaminacyjne() {
		return pytaniaEgzaminacyjne;
	}

	private void setPytaniaEgzaminacyjne(List<PytanieEgzaminacyjne> pytaniaEgzaminacyjne) {
		this.pytaniaEgzaminacyjne = pytaniaEgzaminacyjne;
	}
	@OneToOne
	private Ocena getOcena() {
		return ocena;
	}

	private void setOcena(Ocena ocena) {
		this.ocena = ocena;
	}
	@Transient
	public void uruchom() {}
	
	@Transient
	public Ocena zakonczIocen() {
		return null;
	}
	
	@Transient
	public void edytuj() {}
	
	@Transient
	public void udostepnij(PrzedmiotGrupa przedmiotGrupa) {
		
	}
	
	@Transient
	public void usun() {}
	
	@Transient
	public void pokazWyniki() {}

	@Override
	public String toString() {
		return "Egzamin " + tytul + " z przedmiotu - " + przedmiot;
	};
	
	
	
}
