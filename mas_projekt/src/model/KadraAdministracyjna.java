package model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

@Entity(name = "KadraAdministracyjna")
public class KadraAdministracyjna {

	private long id;
	private String nazwaStanowiska;
	private String zakresObowiazkow;
	
	public KadraAdministracyjna() {}
	
	public KadraAdministracyjna(String nazwaStanowiska, String zakresObowiazkow) {
		super();
		this.nazwaStanowiska = nazwaStanowiska;
		this.zakresObowiazkow = zakresObowiazkow;
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

	private void setNazwaStanowiska(String nazwaStanowiska) {
		this.nazwaStanowiska = nazwaStanowiska;
	}

	private void setZakresObowiazkow(String zakresObowiazkow) {
		this.zakresObowiazkow = zakresObowiazkow;
	}

	public void sprawdzWyniki() {
		
	}
	
	public void przypiszUczniaDoKlasy() {
		
	}
	
	public void przypiszNauczycielaDoPrzedmiotu() {
		
	}

	@Basic
	private String getNazwaStanowiska() {
		return nazwaStanowiska;
	}
	@Basic
	private String getZakresObowiazkow() {
		return zakresObowiazkow;
	}

	@Override
	public String toString() {
		return "Administrator";
	}
}
