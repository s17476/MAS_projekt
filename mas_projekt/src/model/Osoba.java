package model;

import java.time.LocalDate;

import javax.persistence.*;
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

	
	@Basic
	private String getPassword() {
		return password;
	}



	private void setPassword(String password) {
		this.password = password;
	}



	@Basic
	private String getImie() {
		return imie;
	}
	
	private void setImie(String imie) {
		this.imie = imie;
	}
	@Basic
	private String getNazwisko() {
		return nazwisko;
	}

	private void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	@Id
	private String getPesel() {
		return pesel;
	}

	private void setPesel(String pesel) {
		this.pesel = pesel;
	}
	@Basic
	private LocalDate getDataUrodzenia() {
		return dataUrodzenia;
	}
	
	private void setDataUrodzenia(LocalDate dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}
	@Basic
	private String getNrKontaBankowego() {
		return nrKontaBankowego;
	}

	private void setNrKontaBankowego(String nrKontaBankowego) {
		this.nrKontaBankowego = nrKontaBankowego;
	}
	@OneToOne
	private Adres getAdres() {
		return adres;
	}

	private void setAdres(Adres adres) {
		this.adres = adres;
	}
	@Basic
	private LocalDate getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	private void setDataZatrudnienia(LocalDate dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}
	@Basic
	private LocalDate getDataRozwiazaniaUmowyOprace() {
		return dataRozwiazaniaUmowyOprace;
	}

	private void setDataRozwiazaniaUmowyOprace(LocalDate dataRozwiazaniaUmowyOprace) {
		this.dataRozwiazaniaUmowyOprace = dataRozwiazaniaUmowyOprace;
	}
	@OneToOne
	private KadraAdministracyjna getAdministrator() {
		return administrator;
	}

	private void setAdministrator(KadraAdministracyjna administrator) {
		this.administrator = administrator;
	}
	@OneToOne
	private KadraDydaktyczna getDydaktyk() {
		return dydaktyk;
	}

	private void setDydaktyk(KadraDydaktyczna dydaktyk) {
		this.dydaktyk = dydaktyk;
	}
	
	@Override
	public String toString() {
		return this.imie+" "+this.nazwisko;
	}
	
	
}
