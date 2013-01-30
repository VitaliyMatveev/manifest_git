package ru.komiparma.manifest.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

@Entity
@Table(name="waybill")
public class WayBill{
	/**
	 * 
	 */
	@Id 
	@GeneratedValue
	@Column(name="waybill_id")
	private Integer id = null;
	
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	/*@Version
	@Column(name="version")
	private Integer version;
	
		public void setVersion(Integer version) {
			this.version = version;
		}
		public Integer getVersion() {
			return version;
		}*/

	@Column(name="kod")
	private Integer kod;
	
		public Integer getKod() {
			return kod;
		}
		public void setKod(Integer kod) {
			this.kod = kod;
		}
		public void setKod(String kod){
			if((kod.trim()).isEmpty()){
				this.kod=0;
			}else{
				this.kod=Integer.parseInt(kod);
			}
		}

	@Column(name="title")
	private String title;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

	@Column(name="fedex")
	private String fedex;
	
		public String getFedex() {
			return fedex;
		}
		public void setFedex(String fedex) {
			this.fedex = fedex;
		}

	@Column(name="shippingType")
	private Integer shippingType;
	
		public Integer getShippingType() {
			return shippingType;
		}
		public void setShippingType(Integer shippingType) {
			this.shippingType = shippingType;
		}

	@Column(name="comments")
	private String comments;
	
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}

	@Column(name ="cargoDescription")
	private String cargoDescription;
	
		public String getCargoDescription() {
			return cargoDescription;
		}
		public void setCargoDescription(String cargoDescription) {
			this.cargoDescription = cargoDescription;
		}

	@Column(name="payType")
	private Integer payType;
	
		public String getPayType(){
			if(this.payType!=null){
				if(this.payType==0)
					return "Наличные";
				else 
					return "Безналичный";
			} else return null;
		}
		public void setPayType(Integer payType) {
			this.payType = payType;
		}
		public void setPayType(String str){
			if(str.equals("Нал"))
				this.payType=0;
			if(str.equals("Б/Нал"))
				this.payType=1;
		}
	
	@Column(name="whoWillPay")
	private Integer whoWillPay;
	
		public String getWhoWillPay() {
			if(this.whoWillPay!=null){
				if(this.whoWillPay==0)
					return "Отправитель";
				else if(this.whoWillPay==1)
					return "Получатель";
				else
					return "3-я сторона";
			}else 
				return null;
		}
		public void setWhoWillPay(Integer whoWillPay) {
			this.whoWillPay = whoWillPay;
		}
		public void setWhoWillPay(String str){
			if(str.equals("Отправитель"))
				this.whoWillPay=0;
			if(str.equals("Получатель"))
				this.whoWillPay=1;
			if(str.equals("3-я сторона"))
				this.whoWillPay=2;
		}
	
	@Temporal(TemporalType.DATE)
	@Column (name="date_create")
	private Date dateCreate;
		
		public Date getDateCreate() {
			return dateCreate;
		}
		public void setDateCreate(String str){
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				this.dateCreate=sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		public void setDateCreate(Date dateCreate) {
			this.dateCreate = dateCreate;
		}
	
	@Column(name="volume")
	private Double volume;
	
		public Double getVolume() {
			return volume;
		}
		public void setVolume(Double volume) {
			this.volume = volume;
		}
		public void setVolume(String volume) {
			if((volume.trim()).isEmpty())
				this.volume=0.0;
			else
				this.volume=Double.valueOf(volume);
		}
		
	@Column(name="counts")
	private Integer counts;
		
		public Integer getCounts() {
			return counts;
		}
		public void setCounts(Integer counts) {
			this.counts = counts;
		}
		public void setCounts(String counts) {
			if((counts.trim()).isEmpty())
				this.counts=0;
			else
				this.counts=Integer.parseInt(counts);
		}
	
	@Column(name="weight")
	private Double weight;
	
		public Double getWeight() {
			return weight;
		}
	
		public void setWeight(Double weight) {
			this.weight = weight;
		}
		
		public void setWeight(String weight){
			if((weight.trim()).isEmpty())
				this.weight=0.0;
			else
				this.weight=Double.valueOf(weight);
			
		}
	
	@Column(name="specialConditions")
	private Integer specialConditions;
		
		public String getSpecialConditions() {
			if(this.specialConditions!=null){
				if(this.specialConditions==0)
					return "срочный";
				else if (this.specialConditions==1)
					return "суббота";
				else 
					return " ";
			} else 
				return null;
		}
		public void setSpecialConditions(Integer specialConditions) {
			this.specialConditions = specialConditions;
		}
		public void setSpecialConditions(String str) {
			if(str.equals("срочно"))
				this.specialConditions=0;
			else if (str.equals("суббота"))
				this.specialConditions=1;
			else
				this.specialConditions=2;
		}
	
	@Column(name="directTransport")
	private Boolean directTransport;
	
		public Boolean getDirectTransport() {
			return directTransport;
		}
		public void setDirectTransport(Boolean directTransport) {
			this.directTransport = directTransport;
		}
		public void setDirectTransport(String str){
			if(str.contains("Москва"))
				this.directTransport=false;
			else this.directTransport=true;
		}

	@Column(name="insurance")
	private Double insurance;
	
		public Double getInsurance() {
			return insurance;
		}
		public void setInsurance(Double insurance) {
			this.insurance = insurance;
		}
		public void setInsurance(String insurance) {
			if((insurance.trim()).isEmpty())
				this.insurance=0.0;
			else
				this.insurance=Double.valueOf(insurance);
		}
		
	@Column(name="transportation")
	private Double transportation;
	
		public Double getTransportation() {
			return transportation;
		}
		public void setTransportation(Double transportation) {
			this.transportation = transportation;
		}
		public void setTransportation(String transportation) {
			if((transportation.trim()).isEmpty())
				this.transportation=0.0;
			else
				this.transportation=Double.valueOf(transportation);
		}

	@Column(name="recipient_fio")
	private String recipient_fio;
	
		public String getRecipient_fio() {
			return recipient_fio;
		}
		public void setRecipient_fio(String recipient_fio) {
			this.recipient_fio = recipient_fio;
		}

	@Column(name="sender_fio")
	private String sender_fio;
	
		public String getSender_fio() {
			return sender_fio;
		}
		public void setSender_fio(String sender_fio) {
			this.sender_fio = sender_fio;
		}

	
	/***********************************************************************************
	 *                          TODO Mapping property                                  *
	 ***********************************************************************************/

	@Sort(type=SortType.NATURAL)
	@OneToMany(cascade={CascadeType.ALL},mappedBy="waybill")
	private SortedSet<Status> statusList = new TreeSet<Status>();

		public SortedSet<Status> getStatusList() {
			return statusList;
		}
		public void setStatusList(SortedSet<Status> statusList) {
				this.statusList = statusList;
		}
		
	@ManyToOne
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="recipient_id")
	private Client recipient;
	
		public Client getRecipient() {
			return recipient;
		}
		public void setRecipient(Client recipient) {
			this.recipient = recipient;
		}

	@ManyToOne
	@JoinColumn(name="sender_id")
	private Client sender;
		
		public Client getSender() {
			return sender;
		}
		public void setSender(Client sender) {
			this.sender = sender;
		}

	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="trackingcompany_id")
	private TrackingCompany trackCompany;
		
		public TrackingCompany getTrackCompany() {
			return trackCompany;
		}
		public void setTrackCompany(TrackingCompany trackCompany) {
			this.trackCompany = trackCompany;
		}

	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="manifest_id")
	private Manifest manifest;
		
		public Manifest getManifest() {
			return manifest;
		}
		public void setManifest(Manifest manifest) {
			this.manifest = manifest;
		}

	@ManyToOne
	@JoinColumn(name="routingsheet_id",insertable=false,updatable=false)
	private RoutingSheet routingsheet;
		
		public RoutingSheet getRoutingsheet() {
			return routingsheet;
		}
		public void setRoutingsheet(RoutingSheet routingsheet) {
			this.routingsheet = routingsheet;
		}
		
	/*@Column
	private int indxRoutingSheet;
	
	public int getIndxRoutingSheet() {
		return indxRoutingSheet;
	}
	public void setIndxRoutingSheet(int indxRoutingSheet) {
		this.indxRoutingSheet = indxRoutingSheet;
	}*/
	/***********************************************************************************
	 *                               Methods                                           *
	 ***********************************************************************************/	
		
	public void addStatus(Status statusToAdd){
		statusToAdd.setWayBill(this);
		this.statusList.add(statusToAdd);
	}
	
	public void removeStatus(Status statusToRemove){
		getStatusList().remove(statusToRemove);
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
		WayBill other = (WayBill) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.title;
	}
	
	
}
