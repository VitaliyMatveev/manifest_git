package ru.komiparma.manifest;

public class HistoryOfWayBillFromMega {
	private String event;
	private String city;
	private String date;
	private String comments;
	
	public HistoryOfWayBillFromMega() {
	}
	
	public HistoryOfWayBillFromMega(String event,String city,String date,String comments) {
		this.event=event;
		this.city=city;
		this.date=date;
		this.comments=comments;
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	 
	
}
