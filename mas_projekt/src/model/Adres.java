package model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

@Entity(name = "Adres")
public class Adres {
	
	private long id;
	private String ulica;
	private String nrDomu;
	private String miejscowosc;
	private String kodPocztowy;
	private String telefon;
	
	public Adres() {
	}

	public Adres(String ulica, String nrDomu, String miejscowosc, String kodPocztowy, String telefon) {
		super();
		this.ulica = ulica;
		this.nrDomu = nrDomu;
		this.miejscowosc = miejscowosc;
		this.kodPocztowy = kodPocztowy;
		this.telefon = telefon;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy  = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Basic
	private String getUlica() {
		return ulica;
	}

	@Basic
	private String getNrDomu() {
		return nrDomu;
	}

	@Basic
	private String getMiejscowosc() {
		return miejscowosc;
	}

	@Basic
	private String getKodPocztowy() {
		return kodPocztowy;
	}

	@Basic
	private String getTelefon() {
		return telefon;
	}

	private void setUlica(String ulica) {
		this.ulica = ulica;
	}

	private void setNrDomu(String nrDomu) {
		this.nrDomu = nrDomu;
	}

	private void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	private void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	private void setTelefon(String telefon) {
		this.telefon = telefon;
	}
}
