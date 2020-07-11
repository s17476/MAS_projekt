package controller;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.Grupa;
import model.KadraAdministracyjna;
import model.KadraDydaktyczna;
import model.Osoba;
import model.Przedmiot;
import model.PrzedmiotGrupa;
import model.Uczen;
import view.LogIn;
import view.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		DbController db= new DbController<>();
		/**
		 * przedmioty
		 */
		var p1 = new Przedmiot("Modelowanie i analiza danych", "MAS", "Przedmiot prowadzony przez internet. Techniki zaawansowane w Java");
		db.save(p1);
		var p2 = new Przedmiot("Statystyczna analiza danych", "SAD", "Statystyka");
		db.save(p2);
		var p3 = new Przedmiot("Aplikacje baz danych", "APBD", "Programowanie sieciowe w C#");
		db.save(p3);
		List<Przedmiot> listaPrzedmiotow = new ArrayList<>();
		listaPrzedmiotow.add(p1);
		listaPrzedmiotow.add(p2);
		listaPrzedmiotow.add(p3);
		
		/**
		 * grupy
		 */
		var g1 = new Grupa(LocalDate.parse("2020-09-01"), "1a");
		db.save(g1);
		var g2 = new Grupa(LocalDate.parse("2019-10-01"), "1b");
		db.save(g2);
		
		
		/**
		 * przedmiotGrupa
		 */
		var pg1 = new PrzedmiotGrupa(p1, g1, null);
		db.save(pg1);
		var pg2 = new PrzedmiotGrupa(p2, g1, null);
		db.save(pg2);
		var pg3 = new PrzedmiotGrupa(p3, g2, null);
		db.save(pg3);
		var pg4 = new PrzedmiotGrupa(p1, g2, null);
		db.save(pg4);
		List<PrzedmiotGrupa> listaPrzedmiotowGrup = new ArrayList<>();
		listaPrzedmiotowGrup.add(pg1);
		listaPrzedmiotowGrup.add(pg2);
		listaPrzedmiotowGrup.add(pg3);
		listaPrzedmiotowGrup.add(pg4);
		
		/**
		 * osoby i pełnione funkcje
		 */
		var o1 = new Osoba("Arek", "Arkowski", "1234", "2", "1990-01-02");
		var u1 = new Uczen(LocalDate.parse("2020-10-01"), null, null, null);
		
		o1.setUczen(u1);
		db.save(u1);
		db.save(o1);
		
		var o2 = new Osoba("Marek", "Markowski", "1234", "1", "1990-01-02");
		var n1 = new KadraDydaktyczna(listaPrzedmiotowGrup);
		var a1 = new KadraAdministracyjna("Dziekan", "Przykładowy opis");
		o2.setAdministrator(a1);;
		o2.setDydaktyk(n1);
		db.save(a1);
		db.save(n1);
		db.save(o2);
		
		
		
		System.out.println(o1);
		
		
		
		
		//var osoby = db.load("from Osoba");
		
		//System.out.println(osoby);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LogIn("Log in please...", db);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});


		
		
	}
}
	
