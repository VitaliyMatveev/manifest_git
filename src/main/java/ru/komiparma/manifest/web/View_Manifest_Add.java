package ru.komiparma.manifest.web;

import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.Manifest;
import ru.komiparma.manifest.service.ICityService;
import ru.komiparma.manifest.service.IManifestService;

@Component
@Scope("request")
public class View_Manifest_Add {

	@Autowired
	private IManifestService mnfSrv;
	@Autowired
	private ICityService citySrv;
	
	private String title;
	private Date dateRecive;
	private Integer cityId;
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public View_Manifest_Add(){
		System.out.println("add");
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
		
		this.dateRecive=dateRecive;
	}
	
	public void addMnf(ActionEvent event) {
		Manifest mnf = new Manifest(title);
		java.sql.Date date =new java.sql.Date(0);
		date.setTime(dateRecive.getTime());
		mnf.setDateRecive(date);
		mnf.setCity(this.citySrv.get(cityId));
		mnfSrv.save(mnf);
	}
}
