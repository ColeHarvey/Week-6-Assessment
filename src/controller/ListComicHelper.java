package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListComic;

public class ListComicHelper {
	static EntityManagerFactory emfactory =
			Persistence.createEntityManagerFactory("ComicsList");
	
	public void insertItem(ListComic li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListComic> showAllComics(){
		EntityManager em = emfactory.createEntityManager();
		List<ListComic> allComics = em.createQuery("SELECT i FROM ListComic i").getResultList();
		return allComics;
	}
	
	public void deleteItem(ListComic toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic>typedQuery = em.createQuery("SELECT li FROM ListComic li WHERE "
				+ "li.writer = :selectedWriter and li.artist = :selectedArtist and li.publisher = :selectedPublisher", ListComic.class);
		
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedWriter", toDelete.getWriter());
		typedQuery.setParameter("selectedArtist", toDelete.getArtist());
		typedQuery.setParameter("selectedPublisher", toDelete.getPublisher());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		ListComic result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public ListComic searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListComic found = em.find(ListComic.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(ListComic toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<ListComic> searchForItemByWriter(String writerName) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic> typedQuery = em.createQuery("SELECT li FROM ListComic"
				+ " li WHERE li.writer = :selectedWriter", ListComic.class);
		typedQuery.setParameter("selectedWriter", writerName);
		List<ListComic> foundComics = typedQuery.getResultList();
		em.close();
		return foundComics;
	}

	public List<ListComic> searchForItemByArtist(String artistName) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic> typedQuery = em.createQuery("SELECT li FROM ListComic"
				+ " li WHERE li.artist = :selectedArtist", ListComic.class);
		typedQuery.setParameter("selectedArtist", artistName);
		List<ListComic> foundComics = typedQuery.getResultList();
		em.close();
		return foundComics;
	}
	
	public List<ListComic> searchForItemByPublisher(String publisherName) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic> typedQuery = em.createQuery("SELECT li FROM ListComic"
				+ " li WHERE li.publisher = :selectedPublisher", ListComic.class);
		typedQuery.setParameter("selectedPublisher", publisherName);
		List<ListComic> foundComics = typedQuery.getResultList();
		em.close();
		return foundComics;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
