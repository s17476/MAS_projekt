package model;

import java.io.File;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity(name = "Uczeń")
public class Uczen {
	
	public long id;
	private LocalDate dataRozpoczecia;
	private Grupa grupa;
	private Osoba opiekun;
	private LocalDate dataUkonczeniaSzkoly;
	
	
	
	public Uczen() {
	}

	public Uczen(LocalDate dataRozpoczecia, Grupa grupa, Osoba opiekun, LocalDate dataUkonczeniaSzkoly) {
		super();
		this.dataRozpoczecia = dataRozpoczecia;
		this.grupa = grupa;
		this.opiekun = opiekun;
		this.dataUkonczeniaSzkoly = dataUkonczeniaSzkoly;
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
	
	@Basic
	private LocalDate getDataRozpoczecia() {
		return dataRozpoczecia;
	}



	private void setDataRozpoczecia(LocalDate dataRozpoczecia) {
		this.dataRozpoczecia = dataRozpoczecia;
	}


	@ManyToOne(cascade=CascadeType.ALL)
	public Grupa getGrupa() {
		return grupa;
	}



	private void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}


	@OneToOne(cascade=CascadeType.ALL)
	private Osoba getOpiekun() {
		return opiekun;
	}



	private void setOpiekun(Osoba opiekun) {
		this.opiekun = opiekun;
	}


	@Basic
	private LocalDate getDataUkonczeniaSzkoly() {
		return dataUkonczeniaSzkoly;
	}



	private void setDataUkonczeniaSzkoly(LocalDate dataUkonczeniaSzkoly) {
		this.dataUkonczeniaSzkoly = dataUkonczeniaSzkoly;
	}


	@Transient
	public void przeslijPraceDomowa(File file) {
		
	}
	@Transient
	public File pobierzMaterialy() {
		return null;
	}
	@Transient
	public void rozwiazEgzamin(Egzamin egzamin) {
		
	}
	@Transient
	public void kontakt(Osoba osoba) {
		
	}
	
	@Transient
	@Override
	public String toString() {
		return "Uczen";
	}
	
}
