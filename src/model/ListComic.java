package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comics")
public class ListComic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="WRITER")
	private String writer;
	@Column(name="ARTIST")
	private String artist;
	@Column(name="PUBLISHER")
	private String publisher;
	
	public ListComic() {
		
	}

	public  ListComic(String writer, String artist, String publisher) {
		this.writer = writer;
		this.artist = artist;
		this.publisher = publisher;
	}
	
	public String returnItemDetails() {
		return writer+ ": " + artist + ": " + publisher;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
