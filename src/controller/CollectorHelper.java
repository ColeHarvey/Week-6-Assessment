package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Collector;



public class CollectorHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleShoppingList");
	
	public void insertShopper(Collector s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	public List<Collector> showAllCollectors() {
		EntityManager em = emfactory.createEntityManager();
		List<Collector> allCollector = em.createQuery("SELECT s FROM Collector s").getResultList();
		return allCollector;
	}	
	
	public Collector findCollector(String nameToLookUp) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Collector> typedQuery = em.createQuery("select sh from Collector sh "
				+ "where sh.collectorName = :selectedName", Collector.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		Collector foundCollector;
		try {
			foundCollector = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundCollector = new Collector(nameToLookUp);
		}
		em.close();
		return foundCollector;
	}
}
