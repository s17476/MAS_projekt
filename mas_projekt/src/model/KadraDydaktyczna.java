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



	public KadraDydaktyczna(long id, List<PrzedmiotGrupa> listaPrzedmiotów) {
		super();
		this.id = id;
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
	private void dodajPrzedmiot(PrzedmiotGrupa przedmiot) {
		listaPrzedmiotów.add(przedmiot);
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<PrzedmiotGrupa> getListaPrzedmiotów() {
		return listaPrzedmiotów;
	}


	private void setListaPrzedmiotów(List<PrzedmiotGrupa> listaPrzedmiotów) {
		this.listaPrzedmiotów = listaPrzedmiotów;
	}

	public void sprawdzWyniki() {}
	
	
}
