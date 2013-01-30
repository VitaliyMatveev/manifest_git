package ru.komiparma.manifest.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="routingsheet")
public class RoutingSheet {
	

	@Id 
	@GeneratedValue
	@Column(name="routingsheet_id")
	private Integer id = null;
	
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Column(name="title")
	private String title;
	
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

	@Column(name="date_created")
	private Date dateCreated;
	
		public Date getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}

	@Column(name="comments")
	private String comments;
	
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}

	/***********************************************************************************
	 *                          TODO Mapping property                                  *
	 ***********************************************************************************/
	
	@OneToMany(fetch=FetchType.EAGER,cascade={javax.persistence.CascadeType.ALL})
	@JoinColumn(name="routingsheet_id")
	@IndexColumn(name="indxRoutingSheet",base=0,nullable=false)
	private List<WayBill> wayBillList = new LinkedList<WayBill>();
		public List<WayBill> getWayBillList() {
			return wayBillList;
		}
		public void setWayBillList(List<WayBill> wayBillList) {
			this.wayBillList = wayBillList;
		}

	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade({CascadeType.REFRESH})
	@JoinColumn(name="courier_id")
	private Courier courier;
	
		public Courier getCourier() {
			return courier;
		}
		public void setCourier(Courier courier) {
			this.courier = courier;
		}
		
	/***********************************************************************************
	 *                          TODO Constructor                                       *
	 ***********************************************************************************/
	public RoutingSheet(){
		this.setDateCreated(Calendar.getInstance().getTime());
	}
	
	public RoutingSheet(Date dateCreated,String courierId) {
		this.dateCreated=dateCreated;
	}
	/**
	 * Adding waybill to routingsheet
	 * 
	 * @param wayBillToAdd
	 */
	public void addWayBill(WayBill wayBillToAdd){
		wayBillToAdd.setRoutingsheet(this);
		getWayBillList().add(wayBillToAdd);
	}

	public void removeWayBill(WayBill wayBillToRemove){
		if(getWayBillList().contains(wayBillToRemove)){
			wayBillToRemove.setRoutingsheet(null);
			getWayBillList().remove(wayBillToRemove);
		}
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
		RoutingSheet other = (RoutingSheet) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	
}

