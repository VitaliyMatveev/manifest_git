package ru.komiparma.manifest.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

@Entity
@Table(name="manifest")
public class Manifest{



	@Id
	@Column(name="manifest_id")
	@GeneratedValue
	private Integer id = null;
	

	@Column(name="title")
	private String title;
	
	@Column(name="date_recive")
	private Date dateRecive;
	
	@Column(name="date_delivery")
	private Date dateDelivery;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="manifest")
	private List<WayBill> wayBillList=new ArrayList<WayBill>();

	public Manifest(){
		
	}
	
	public Manifest(String title){
		this.title=title;
	}
	
	public Manifest(String title,Date dateRecive){
		this.title=title;
		this.dateRecive=dateRecive;
	}
	/**
	 * Adding waybill to manifest
	 * 
	 * @param wayBillToAdd
	 */
	public void addWayBill(WayBill wayBillToAdd){
		wayBillToAdd.setManifest(this);
		getWayBillList().add(wayBillToAdd);
	}

	/**
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

	public Date getDateRecive() {
		return dateRecive;
	}

	public void setDateRecive(Date dateRecive) {
		this.dateRecive = dateRecive;
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<WayBill> getWayBillList() {
		return wayBillList;
	}

	public void setWayBillList(List<WayBill> wayBillList) {
		this.wayBillList = wayBillList;
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
		Manifest other = (Manifest) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

}
