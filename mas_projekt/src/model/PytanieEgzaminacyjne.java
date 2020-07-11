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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "PytanieEgzaminacyjne")
public class PytanieEgzaminacyjne {

	public long id;
	public String trescPytania;
	public List<String> zleOdpowiedzi = new ArrayList<>();
	public List<String> dobreOdpowiedzi = new ArrayList<>();
	
	
	
	public PytanieEgzaminacyjne() {
	}

	public PytanieEgzaminacyjne(String trescPytania) {
		super();
		this.trescPytania = trescPytania;
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
	private String getTrescPytania() {
		return trescPytania;
	}

	private void setTrescPytania(String trescPytania) {
		this.trescPytania = trescPytania;
	}
	
	@ElementCollection(targetClass = String.class)
	private List<String> getZleOdpowiedzi() {
		return zleOdpowiedzi;
	}

	private void setZleOdpowiedzi(List<String> zleOdpowiedzi) {
		this.zleOdpowiedzi = zleOdpowiedzi;
	}
	
	@ElementCollection(targetClass = String.class)
	private List<String> getDobreOdpowiedzi() {
		return dobreOdpowiedzi;
	}

	private void setDobreOdpowiedzi(List<String> dobreOdpowiedzi) {
		this.dobreOdpowiedzi = dobreOdpowiedzi;
	}
	
	
}
