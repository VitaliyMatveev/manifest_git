package ru.komiparma.manifest.web;

import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.Manifest;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.IManifestService;

@Component
@Scope("request")
public class CopyOfView_WayBill_Add {

	@Autowired
	private IManifestService mnfSrv;
	
	private String title;
	private Integer cityId;
	private String city;
	private Integer manifestId;
	private String phone;
	private String address;
	private String company;
	private String fio;
	private Integer weight;
	private Integer count;
	



	public CopyOfView_WayBill_Add(){
		System.out.println("------------------");
		System.out.println("create way bill add");
		System.out.println("------------------");		
	}
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getCityId() {
		return cityId;
	}


	public void setCityId(Integer cityId) {
		this.cityId = cityId;
		this.manifestId=null;
	}
	
	public void cityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void manifestId(Integer manifestId) {
		this.manifestId = manifestId;
	}


	public Integer getManifestId() {
		return manifestId;
	}


	public void setManifestId(Integer manifestId) {
		this.manifestId = manifestId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getFio() {
		return fio;
	}


	public void setFio(String fio) {
		this.fio = fio;
	}
	
	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}

	public void onWayBillAdd(ActionEvent event){
		WayBill wb = new WayBill();
		wb.setTitle(title);
		//wb.setWeight(weight);
		wb.setCounts(count);
		Manifest mnf = mnfSrv.get(manifestId);
		mnf.addWayBill(wb);
		mnfSrv.save(mnf);
	}
	

}
