package controller;

import java.awt.EventQueue;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.Osoba;
import view.MainWindow;

public class Main {
	
	public static void main(String[] args) {

		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;
		 
		try {
		    registry = new StandardServiceRegistryBuilder()
		            .configure() // configures settings from hibernate.cfg.xml
		            .build();
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		 
		    Session session = sessionFactory.openSession();
		    session.beginTransaction();
		    Osoba o1 = new Osoba("Adam", "Adamczewski", "1234", "123456", "2004-04-05");
		    
		    session.save(o1);
			
		    System.out.println(session.getTransaction().isActive());
		    
		    session.getTransaction().commit();
		    session.beginTransaction();
		    List<Osoba> o2 = (List<Osoba>) session.createQuery("from Osoba").list();
		    
		    
		    System.out.println(o2);
		    session.getTransaction().commit();
		    session.close();
		}
		catch (Exception e) {
		    e.printStackTrace();
		    StandardServiceRegistryBuilder.destroy( registry );
		}
		finally {
		    if (sessionFactory != null) {
		        sessionFactory.close();
		        sessionFactory = null;
		    }
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
	
