package ru.komiparma.manifest.web;

import java.util.Date;

import org.primefaces.model.DefaultScheduleEvent;

public class ExtendsScheduleEvent extends DefaultScheduleEvent{		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1066057024138579541L;
	private String type;
	
	public ExtendsScheduleEvent(String title, Date start, Date end, String type) {
		super(title, start, end);
		this.type=type;
		// TODO Auto-generated constructor stub
	}
	
	public ExtendsScheduleEvent(){
		super();
	}
	
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
	
}

