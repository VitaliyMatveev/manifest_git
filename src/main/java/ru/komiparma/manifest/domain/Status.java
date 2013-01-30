package ru.komiparma.manifest.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="status")
public class Status implements Comparable<Status>{

	@Id 
	@GeneratedValue
	@Column(name="status_id")
	private Integer id = null;
	
	@Column(name="title")
	private String title = null;
	
	@Column(name="eventDate")
	private Date eventDate;
	
	@Column(name="comments")
	private String comments;
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="waybill_id")
	private WayBill waybill;

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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public WayBill getWayBill() {
		return waybill;
	}

	public void setWayBill(WayBill waybill) {
		this.waybill = waybill;
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
		Status other = (Status) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Status o) {
		return this.getEventDate().compareTo(o.getEventDate());
	}
	
	
}
