package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Grupa")
public class Grupa {
	
	public long id;
	public LocalDate rokSzkolny;
	public String numerGrupy;
	public List<Uczen> listaUcznow = new ArrayList<>();
	
	
	
	public Grupa() {
	}

	public Grupa(LocalDate rokSzkolny, String numerGrupy, List<Uczen> listaUcznow) {
		super();
		this.rokSzkolny = rokSzkolny;
		this.numerGrupy = numerGrupy;
		this.listaUcznow = listaUcznow;
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
	private LocalDate getRokSzkolny() {
		return rokSzkolny;
	}
	
	
	private void setRokSzkolny(LocalDate rokSzkolny) {
		this.rokSzkolny = rokSzkolny;
	}
	@Basic
	private String getNumerGrupy() {
		return numerGrupy;
	}

	private void setNumerGrupy(String numerGrupy) {
		this.numerGrupy = numerGrupy;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Uczen> getListaUcznow() {
		return listaUcznow;
	}

	private void setListaUcznow(List<Uczen> listaUcznow) {
		this.listaUcznow = listaUcznow;
	}
	
	
}
