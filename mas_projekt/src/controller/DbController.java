package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import model.Osoba;


public class DbController<E> {
	StandardServiceRegistry registry = null;
	SessionFactory sessionFactory = null;
	Session session;

	public DbController() {
		
		 
		try {
		    registry = new StandardServiceRegistryBuilder()
		            .configure() // configures settings from hibernate.cfg.xml
		            .build();
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		 
		    session = sessionFactory.openSession();

		    //List<Osoba> o2 = (List<Osoba>) session.createQuery("from Osoba").list();
		}
		catch (Exception e) {
		    e.printStackTrace();
		    StandardServiceRegistryBuilder.destroy( registry );
		}

		
		
	}
	
	
	public Query<E> createQuery(CriteriaQuery<E> cr){
		return session.createQuery(cr);
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return session.getCriteriaBuilder();
	}
	
	public void save(List<E> list) {
		session.beginTransaction();
		list.stream().forEach(o -> session.save(o));
		session.getTransaction().commit();
		

	}
	
	public void save(E object) {
		List<E> list = new ArrayList<E>();
		list.add(object);
		save(list);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<E> load(String s){
		session.beginTransaction();
		//List<Osoba> o2 = (List<Osoba>) session.createQuery("from Osoba").list();
		List<E> tmpList = session.createQuery(s).list();
		return tmpList;
		
	}
	
	public void close() {
		try {
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
	}
}