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
@Table(name="trackingcompany")
public class TrackingCompany {
	
	@Id 
	@GeneratedValue
	@Column(name="trackingcompany_id")
	private Integer id = null;
	
	@Column(name="title")
	private String title;
	
	@Column(name="phone")
	private String phone;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="trackCompany")
	private List<WayBill> wayBillList=new ArrayList<WayBill>();
	
	public void addWayBill(WayBill wayBillToAdd){
		wayBillToAdd.setTrackCompany(this);
		this.wayBillList.add(wayBillToAdd);
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		TrackingCompany other = (TrackingCompany) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
