package model;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

@Entity(name = "Osoba")
public class Osoba {
	
	private String imie;
	private String nazwisko;
	private String password;
	private String pesel;
	private LocalDate dataUrodzenia;
	private String nrKontaBankowego;
	private Adres adres;
	private LocalDate dataZatrudnienia;
	private LocalDate dataRozwiazaniaUmowyOprace;
	private Uczen uczen;
	private KadraAdministracyjna administrator;
	private KadraDydaktyczna dydaktyk;
	
	public Osoba() {}
	
	public Osoba(String imie, String nazwisko, String password, String pesel, String dataUrodzenia) {///////////////do usunięcia
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.password = password;
		this.pesel = pesel;
		this.dataUrodzenia = LocalDate.parse(dataUrodzenia);
	}

	@OneToOne
	public Uczen getUczen() {
		return uczen;
	}

	public void setUczen(Uczen uczen) {
		this.uczen = uczen;
	}
	
	@Basic
	private String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	@Basic
	public String getImie() {
		return imie;
	}
	
	public void setImie(String imie) {
		this.imie = imie;
	}
	@Basic
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	@Id
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	@Basic
	public LocalDate getDataUrodzenia() {
		return dataUrodzenia;
	}
	
	public void setDataUrodzenia(LocalDate dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}
	@Basic
	public String getNrKontaBankowego() {
		return nrKontaBankowego;
	}

	public void setNrKontaBankowego(String nrKontaBankowego) {
		this.nrKontaBankowego = nrKontaBankowego;
	}
	@OneToOne
	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	@Basic
	public LocalDate getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}
	@Basic
	public LocalDate getDataRozwiazaniaUmowyOprace() {
		return dataRozwiazaniaUmowyOprace;
	}

	public void setDataRozwiazaniaUmowyOprace(LocalDate dataRozwiazaniaUmowyOprace) {
		this.dataRozwiazaniaUmowyOprace = dataRozwiazaniaUmowyOprace;
	}
	@OneToOne
	public KadraAdministracyjna getAdministrator() {
		return administrator;
	}

	public void setAdministrator(KadraAdministracyjna administrator) {
		this.administrator = administrator;
	}
	@OneToOne
	public KadraDydaktyczna getDydaktyk() {
		return dydaktyk;
	}

	public void setDydaktyk(KadraDydaktyczna dydaktyk) {
		this.dydaktyk = dydaktyk;
	}
	
	@Override
	public String toString() {
		return this.imie+" "+this.nazwisko+" "+this.pesel+" "+this.password;
	}
}
