package ru.komiparma.manifest.web;

import java.io.Serializable;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.City;
import ru.komiparma.manifest.domain.Manifest;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.ICityService;
import ru.komiparma.manifest.service.IManifestService;
import ru.komiparma.manifest.service.IWayBillService;

@Component
@ApplicationScoped
public class View_Main implements Serializable{
	
	/**
	 *  
	 */
	private static final long serialVersionUID = -8646794777916266563L;

	
	private IManifestService mnfService;
	private ICityService citySrv;
	private IWayBillService wbSrv;
	
	private WayBill selectedWayBill;
	private Integer cityId;
	private Integer manifestId;
	private Integer activeCityTab;
	private Integer [] activeManifestTab = {0,0};
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getManifestId() {
		return manifestId;
	}

	public void setManifestId(Integer manifestId) {
		this.manifestId = manifestId;
	}

	@Autowired
	public View_Main(IManifestService mnfSrv,ICityService citySrv,IWayBillService wbSrv){
		this.mnfService=mnfSrv;
		this.citySrv=citySrv;
		this.wbSrv=wbSrv;
		System.out.println("------------------");
		System.out.println("create MAIN VIEW");
		System.out.println("------------------");
		//install();
	}
	
	public void install(){
		this.mnfService.removeAll();
	}
	
	public List <Manifest> actualManifestList(String cityTitle){
		return citySrv.get(cityTitle).getManifestList();				
	}
	/*public List <Manifest> actualManifestList(Integer cityId){
		return citySrv.get(cityId).getManifestList();				
	}*/
	
	public List <WayBill> getWayBillList(Integer id){
		this.manifestId=id;
		return this.mnfService.get(id).getWayBillList();
	}
	
	public List<City> getCityList(){
		return this.citySrv.getAll();
	}

	
	public WayBill getSelectedWayBill() {
		return selectedWayBill;
	}

	public void setSelectedWayBill(WayBill selectedWayBill) {
		System.out.println("Active way bill "+selectedWayBill.getId());
		this.selectedWayBill = selectedWayBill;
	}
	
	public Integer getActiveCityTab() {
		return activeCityTab;
	}

	public void setActiveCityTab(Integer activeCityTab) {
		System.out.println("Active city tab "+activeManifestTab);
		this.activeCityTab = activeCityTab;
	}



	public Integer[] getActiveManifestTab() {
		return activeManifestTab;
	}

	public void setActiveManifestTab(Integer[] activeManifestTab) {
		this.activeManifestTab = activeManifestTab;
	}

/*	public void onRowSelect(SelectEvent event){
		System.out.println("======================");
		System.out.println("ROW SELECT!!!!!"+((WayBill)event.getObject()).getTitle());
		this.selectedWayBill=(WayBill)event.getObject();
		System.out.println("====================");
	}*/
	
	public void deleteWayBill(ActionEvent event){
		System.out.println("Start delete wb size before:" +this.wbSrv.getAll().size()+"manifest id:"+selectedWayBill.getManifest().getId());
		Manifest mnf=mnfService.get(selectedWayBill.getManifest().getId());
		mnf.getWayBillList().remove(selectedWayBill);
		wbSrv.remove(selectedWayBill.getId());
		mnfService.save(mnf);
		
		//System.out.println("size after:" +this.wbSrv.getAll().size());
	}

	
	public void  onWayBillAdd(){
		System.out.println("active city tab:"+getActiveCityTab()+" active manifest tab:"+getActiveManifestTab());
		this.cityId=citySrv.getAll().get(getActiveCityTab()).getId();
	//	this.manifestId=actualManifestList(cityId).get(getActiveManifestTab()[getActiveCityTab()]).getId();
	}
	
/*	private UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		    while (kids.hasNext()) {
		UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			 }
		}
		return null;
	}*/
	public void onTest(ActionEvent e){
		System.out.println("Start");
	}
		    
}
