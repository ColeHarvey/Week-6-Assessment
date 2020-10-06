package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.CollectionDetails;


public class CollectionDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ComicsList");
	
	public void insertNewCollectionDetails(CollectionDetails s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<CollectionDetails> getCollections() {
		EntityManager em = emfactory.createEntityManager();
		List<CollectionDetails> allDetails = em.createQuery("SELECT d FROM CollectionDetails d").getResultList();
		return allDetails;
	}
	
	public void deleteCollection(CollectionDetails toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CollectionDetails> typedQuery = em.createQuery("select detail from ListDetails "
				+ "detail where detail.id = :selectedId", CollectionDetails.class);
		
		//Substitute parameter with actual data form the toDelete item
		typedQuery.setParameter("selectedId", toDelete.getId());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		CollectionDetails result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public CollectionDetails searchForListDetailsById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		CollectionDetails found = em.find(CollectionDetails.class, tempId);
		em.close();
		return found;
	}
	public void updateCollection(CollectionDetails toEdit) {
	EntityManager em =emfactory.createEntityManager();
	em.getTransaction().begin();
	em.merge(toEdit);
	em.getTransaction().commit();
	em.close();
	}
}
