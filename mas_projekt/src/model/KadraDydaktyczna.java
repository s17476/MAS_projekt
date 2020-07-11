package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "KadraDydaktyczna")
public class KadraDydaktyczna {
	
	private long id;
	private List<PrzedmiotGrupa> listaPrzedmiotów = new ArrayList<>();
	
	
	
	
	public KadraDydaktyczna() {
	}



	public KadraDydaktyczna(List<PrzedmiotGrupa> listaPrzedmiotów) {
		super();
		this.listaPrzedmiotów = listaPrzedmiotów;
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
	@Transient
	public void dodajPrzedmiot(PrzedmiotGrupa przedmiot) {
		listaPrzedmiotów.add(przedmiot);
		przedmiot.setDydaktyk(this);
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<PrzedmiotGrupa> getListaPrzedmiotów() {
		return listaPrzedmiotów;
	}


	private void setListaPrzedmiotów(List<PrzedmiotGrupa> listaPrzedmiotów) {
		this.listaPrzedmiotów = listaPrzedmiotów;
	}

	public void sprawdzWyniki() {}


	@Transient
	@Override
	public String toString() {
		return "Dydaktyk";
	}
	
	
}
