package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

@Entity(name = "PytanieEgzaminacyjne")
public class PytanieEgzaminacyjne {

	public long id;
	public String trescPytania;
	public List<String> zleOdpowiedzi = new ArrayList<>();
	public List<String> dobreOdpowiedzi = new ArrayList<>();
	public Przedmiot przedmiot;
	
	public PytanieEgzaminacyjne() {}

	public PytanieEgzaminacyjne(String trescPytania, Przedmiot przedmiot) {
		super();
		this.trescPytania = trescPytania;
		this.przedmiot = przedmiot;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy  = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	private long getId() {
		return id;
	}

	@Transient
	public void dodajZlaOdpowiedz(String s) {
		zleOdpowiedzi.add(s);
	}
	
	@Transient
	public void dodajDobraOdpowiedz(String s) {
		dobreOdpowiedzi.add(s);
	}

	private void setId(long id) {
		this.id = id;
	}
	
	@Basic
	public String getTrescPytania() {
		return trescPytania;
	}

	private void setTrescPytania(String trescPytania) {
		this.trescPytania = trescPytania;
	}
	
	@ElementCollection(targetClass = String.class)
	public List<String> getZleOdpowiedzi() {
		return zleOdpowiedzi;
	}

	private void setZleOdpowiedzi(List<String> zleOdpowiedzi) {
		this.zleOdpowiedzi = zleOdpowiedzi;
	}
	
	@ElementCollection(targetClass = String.class)
	public List<String> getDobreOdpowiedzi() {
		return dobreOdpowiedzi;
	}

	private void setDobreOdpowiedzi(List<String> dobreOdpowiedzi) {
		this.dobreOdpowiedzi = dobreOdpowiedzi;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Przedmiot getPrzedmiot() {
		return przedmiot;
	}

	public void setPrzedmiot(Przedmiot przedmiot) {
		this.przedmiot = przedmiot;
	}

	@Override
	public String toString() {
		if(trescPytania.length() > 40)
			return trescPytania.substring(0, 37) + "...";
		return "" +trescPytania;
	}
}
