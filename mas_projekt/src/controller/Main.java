
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

import model.Egzamin;
import model.Grupa;
import model.KadraAdministracyjna;
import model.KadraDydaktyczna;
import model.Osoba;
import model.Przedmiot;
import model.PrzedmiotGrupa;
import model.PytanieEgzaminacyjne;
import model.Uczen;
import view.LogIn;
import view.MainWindow;

/**
 * 
 * @author Grzegorz Frączek
 *
 */

public class Main {
	
	public static void main(String[] args) {
		/**
		 * kontroler Bd
		 */
		DbController db = new DbController<>();
		
		/**
		 * wypełnienie bazy danych
		 */
		//nowaBd();
		
		/**
		 * uruchomienie GUI
		 */
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
	
	public static void nowaBd() {
		/**
		 * przedmioty
		 */
		/*
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
		*/
		
		/**
		 * grupy
		 */
		/*
		var g1 = new Grupa(LocalDate.parse("2020-09-01"), "1a");
		
		var g2 = new Grupa(LocalDate.parse("2019-10-01"), "1b");
		db.save(g2);
		*/
		
		/**
		 * przedmiotGrupa
		 */
		/*
		var pg1 = new PrzedmiotGrupa(p1, g1, null);
		db.save(pg1);
		var pg2 = new PrzedmiotGrupa(p2, g1, null);
		db.save(pg2);
		var pg3 = new PrzedmiotGrupa(p3, g2, null);
		db.save(pg3);
		var pg4 = new PrzedmiotGrupa(p1, g2, null);
		db.save(pg4);
		*/
		
		/**
		 * osoby i pełnione funkcje
		 */
		/*
		var o1 = new Osoba("Arek", "Arkowski", "1234", "2", "1990-01-02");
		var u1 = new Uczen(LocalDate.parse("2020-10-01"), g1, null, null);
		
		o1.setUczen(u1);
		db.save(u1);
		db.save(o1);
		g1.add(u1);
		db.save(g1);
		*/
		
		/**
		 * przedmioty
		 */
		/*
		var o2 = new Osoba("Marek", "Markowski", "1234", "1", "1990-01-02");
		var n1 = new KadraDydaktyczna();
		n1.dodajPrzedmiot(pg1);
		n1.dodajPrzedmiot(pg2);
		n1.dodajPrzedmiot(pg3);
		n1.dodajPrzedmiot(pg4);
		
		var a1 = new KadraAdministracyjna("Dziekan", "Przykładowy opis");
		o2.setAdministrator(a1);;
		o2.setDydaktyk(n1);
		db.save(a1);
		db.save(n1);
		db.save(o2);
		*/
		
		/**
		 * pytania
		 */
		/*
		var pe1 = new PytanieEgzaminacyjne("Zaznacz poprawny wynik działania matematycznego: 2+2", p1);
		pe1.dodajDobraOdpowiedz("4");
		pe1.dodajZlaOdpowiedz("5");
		pe1.dodajZlaOdpowiedz("7");
		pe1.dodajZlaOdpowiedz("22");
		db.save(pe1);
		
		var pe2 = new PytanieEgzaminacyjne("Zaznacz poprawny wynik działania matematycznego: 1+1", p1);
		pe2.dodajDobraOdpowiedz("2");
		pe2.dodajZlaOdpowiedz("5");
		pe2.dodajZlaOdpowiedz("7");
		pe2.dodajZlaOdpowiedz("11");
		db.save(pe2);
		*/
		
		/**
		 * egzamin
		 */
		/*
		var eg1 = new Egzamin("Kolokwium 1", List.of(pe1, pe2), 10, 10, LocalDate.parse("2020-07-07"), LocalDate.parse("2020-07-27"), p1);
		pg1.dodajEgzamin(eg1);
		db.save(eg1);
		
		System.out.println(o1);
		 */
		

		//var osoby = db.load("from Osoba");
		
		//System.out.println(osoby);
	}
}
	
