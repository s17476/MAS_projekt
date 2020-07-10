package model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "ZadanieDomowe")
public class ZadanieDomowe {

	public long id;
	public String tytul;
	public String trescZadania;
	public Ocena ocena;
	
	
	
	public ZadanieDomowe() {
	}


	public ZadanieDomowe(String tytul, String trescZadania, Ocena ocena) {
		super();
		this.tytul = tytul;
		this.trescZadania = trescZadania;
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
	private String getTytul() {
		return tytul;
	}

	private void setTytul(String tytul) {
		this.tytul = tytul;
	}
	@Basic
	private String getTrescZadania() {
		return trescZadania;
	}

	private void setTrescZadania(String trescZadania) {
		this.trescZadania = trescZadania;
	}
	@OneToOne
	private Ocena getOcena() {
		return ocena;
	}

	private void setOcena(Ocena ocena) {
		this.ocena = ocena;
	}
	
	
}
