package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "PrzedmiotGrupa")
public class PrzedmiotGrupa {

	public long id;
	public Przedmiot przedmiot;
	public Grupa grupa;
	public KadraDydaktyczna dydaktyk;
	List<Egzamin> listaEgzaminow = new ArrayList<>();
	List<ZadanieDomowe> listaZadanDomowych = new ArrayList<>();
	
	
	
	public PrzedmiotGrupa() {
	}

	public PrzedmiotGrupa(Przedmiot przedmiot, Grupa grupa, KadraDydaktyczna dudaktyk) {
		super();
		this.przedmiot = przedmiot;
		this.grupa = grupa;
		this.dydaktyk = dudaktyk;
		
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
	private Przedmiot getPrzedmiot() {
		return przedmiot;
	}

	
	private void setPrzedmiot(Przedmiot przedmiot) {
		this.przedmiot = przedmiot;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	private Grupa getGrupa() {
		return grupa;
	}

	private void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	private KadraDydaktyczna getDydaktyk() {
		return dydaktyk;
	}

	private void setDydaktyk(KadraDydaktyczna dudaktyk) {
		this.dydaktyk = dudaktyk;
	}
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Egzamin> getListaEgzaminow() {
		return listaEgzaminow;
	}

	private void setListaEgzaminow(List<Egzamin> listaEgzaminow) {
		this.listaEgzaminow = listaEgzaminow;
	}
	@ManyToMany(cascade=CascadeType.ALL)
	private List<ZadanieDomowe> getListaZadanDomowych() {
		return listaZadanDomowych;
	}

	private void setListaZadanDomowych(List<ZadanieDomowe> listaZadanDomowych) {
		this.listaZadanDomowych = listaZadanDomowych;
	}
	
	
}
