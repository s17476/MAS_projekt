package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

@Entity(name = "Przedmiot")
public class Przedmiot {

	public long id;
	public String nazwaPrzedmiotu;
	public String symbol;
	public String opis;
	
	public Przedmiot() {}

	public Przedmiot(String nazwaPrzedmiotu, String symbol, String opis) {
		super();
		this.nazwaPrzedmiotu = nazwaPrzedmiotu;
		this.symbol = symbol;
		this.opis = opis;
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
	private String getNazwaPrzedmiotu() {
		return nazwaPrzedmiotu;
	}

	private void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
		this.nazwaPrzedmiotu = nazwaPrzedmiotu;
	}

	@Basic
	private String getSymbol() {
		return symbol;
	}

	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Basic
	private String getOpis() {
		return opis;
	}

	private void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "["+symbol+"]"+nazwaPrzedmiotu;
	}
}
