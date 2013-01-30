package ru.komiparma.manifest.domain;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="city")
public class City {

	@Id
	@GeneratedValue
	@Column(name="city_id")
	private Integer id = null;
	

	@Column(name="title")
	private String title;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="city")
	private List<Manifest> manifestList;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="city")
	private List<Courier> courierList=new ArrayList<Courier>();

	/************************************
	 * Constructors
	 */
	public City(){	
	}
	
	public City(String title) {
		super();
		this.title = title;
	}
	
	
	/**
	 * Adding waybill to manifest
	 * 
	 * @param wayBillToAdd
	 */
	public void addManifest(Manifest manifestToAdd){
		manifestToAdd.setCity(this);
		getManifestList().add(manifestToAdd);
	}
	
	public void addCourier(Courier courierToAdd){
		courierToAdd.setCity(this);
		getCourierList().add(courierToAdd);
	}


	/************************************
	 * GETTERS & SETTERS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public List<Manifest> getManifestList() {
		return manifestList;
	}

	public void setManifestList(List<Manifest> manifestList) {
		this.manifestList = manifestList;
	}
	

public List<Courier> getCourierList() {
		return courierList;
	}

	public void setCourierList(List<Courier> courierList) {
		this.courierList = courierList;
	}

public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

/*
 * (non-Javadoc)
 * 
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	City other = (City) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
}