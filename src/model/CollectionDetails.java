package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="collection_details")
public class CollectionDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COLLECTION_ID")
	private int collectionID;
	@Column(name="COLLECTION_NAME")
	private String collectionName;
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="COLLECTOR_ID")
	private Collector collector;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
		@JoinTable
			(
					name="comics_in_collection",
					joinColumns={@JoinColumn(name="COLLECTION_ID", referencedColumnName="COLLECTION_ID") },
					inverseJoinColumns= {@JoinColumn(name="COMIC_ID",referencedColumnName="ID", unique=true) }
			)
	private List<ListComic> listOfComics;
	
	public CollectionDetails () {
		super();
	}
	public CollectionDetails(int id, String collectionName, Collector shopper, List<ListComic> listOfComics) {
		this.collectionID = id;
		this.collectionName = collectionName;
		this.collector = collector;
		this.listOfComics = listOfComics;
	}
	public CollectionDetails(String listName, Collector collector, List<ListComic> listOfComics) {
		this.collectionName = listName;
		this.collector = collector;
		this.listOfComics = listOfComics;
	}
	public CollectionDetails(String collectionName, Collector collector) {
		this.collectionName = collectionName;
		this.collector = collector;
	}
	@Override
	public String toString() {
		return "ListDetails [id=" + collectionID + ", collectionName=" + collectionName + ", collector=" + collector
				+ ", listOfComics=" + listOfComics + "]";
	}
	public int getId() {
		return collectionID;
	}
	public void setId(int id) {
		this.collectionID = id;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public Collector getCollector() {
		return collector;
	}
	public void setShopper(Collector collector) {
		this.collector = collector;
	}
	public List<ListComic> getListOfComics() {
		return listOfComics;
	}
	public void setListOfComics(List<ListComic> listOfComics) {
		this.listOfComics = listOfComics;
	}
}
