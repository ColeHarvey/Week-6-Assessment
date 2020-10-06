package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collector")
public class Collector {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COLLECTOR_ID")
	private int id;
	@Column(name="COLLECTOR_NAME")
	private String collectorName;
	
	public Collector() {
		super();
	}
	public Collector(int id, String collectorName) {
		super();
		this.id = id;
		this.collectorName = collectorName;
	}
	
	public Collector(String collectorName) {
		super();
		this.collectorName = collectorName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}
	@Override
	public String toString() {
		return "Collector [id=" + id + ", collectorName=" + collectorName + "]";
	}
}
