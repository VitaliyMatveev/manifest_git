package ru.komiparma.manifest.domain;


import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="courier")
public class Courier implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5498764089021177969L;

	@Id
	@Column(name="courier_id")
	@GeneratedValue
	private Integer id = null;
	
	@Column(name="fio" )
	private String fio;
	
	@Column(name="phone")
	private Integer phone;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="city_id")
	private City city;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="courier")
	private List <RoutingSheet> routingSheetList=new ArrayList<RoutingSheet>();
	
	/**
	 * Adding routing sheet to courier
	 * 
	 * @param routingSheetToAdd
	 */
	public void addRoutingSheet(RoutingSheet routingSheetToAdd){
		routingSheetToAdd.setCourier(this);
		getRoutingSheetList().add(routingSheetToAdd);
	}
	
	public Courier(){
	}
	
	public Courier(String fio){
		this.fio=fio;
	}
	/**
	 * Setters and Getters
	 */


	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<RoutingSheet> getRoutingSheetList() {
		return routingSheetList;
	}

	public void setRoutingSheetList(List<RoutingSheet> routingSheetList) {
		this.routingSheetList = routingSheetList;
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
		Courier other = (Courier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

