package ru.komiparma.manifest.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.menuitem.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.IWayBillService;

@Component
@ViewScoped
public class View_Unsorted {

	@Autowired
	private IWayBillService wbSrv;
	
	private String city="Коми";
	private List<SelectItem> cities = new ArrayList<SelectItem>();
	private List<WayBill> data = new ArrayList<WayBill>();
	private List<String> selectWBtitle = new ArrayList<String>();
	private WayBill[] selectWB;
	private boolean full;
	
	@Autowired
	public View_Unsorted(IWayBillService wbSrv){
		this.wbSrv=wbSrv;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<SelectItem> getCities() {
		return cities;
	}

	public void setCities(List<SelectItem> cities) {
		this.cities = cities;
	}

	public List<String> getSelectWBtitle() {
		return selectWBtitle;
	}

	public void setSelectWBtitle(List<String> selectWBtitle) {
		this.selectWBtitle = selectWBtitle;
	}

	public WayBill[] getSelectWB() {
		return selectWB;
	}

	public void setSelectWB(WayBill[] selectWB) {
		this.selectWB = selectWB;
		this.selectWBtitle.clear();
		for(WayBill wb:selectWB){
			this.selectWBtitle.add(wb.getTitle());
		}
	}
	private List<WayBill> formatData(){
		List<WayBill> results = new ArrayList<WayBill>();
		List<WayBill> source = this.wbSrv.getAll();
		for(WayBill wb:source){
			if(wb.getRecipient()!=null){
				if(wb.getRecipient().getCity().toLowerCase().contains(this.getCity().toLowerCase())
						&&(!(wb.getRecipient().getCity()).toLowerCase().contains("сыктывкар")))
							results.add(wb);
			}
		}
		return results;
	}
	public void cityChange(ActionEvent e){
		MenuItem mi = (MenuItem) e.getSource();
		//System.out.println(mi.getValue()+" "+this.city);
		if(mi.getValue().equals("Ухта"))
			this.city="Ухта";
		else if(mi.getValue().equals("Инта"))
			this.city="Инта";
		else if(mi.getValue().equals("Воркута"))
			this.city="Воркута";
		else if(mi.getValue().equals("Печора"))
			this.city="Печора";
		else if(mi.getValue().equals("Усинск"))
			this.city="Усинск";
		else
			this.city="Коми";
		data=formatData();
	}

	public void view(){
		for(WayBill wb:selectWB){
			System.out.println(wb.getId()+" "+wb.getTitle());
		}
	}
	
	public List<WayBill> getData() {
			if(data.isEmpty()){
				data=formatData();
		}
		return data;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}
	
	
	
	

}
