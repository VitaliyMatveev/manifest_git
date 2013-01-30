package ru.komiparma.manifest.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.komiparma.manifest.WayBillFromMega;
import ru.komiparma.manifest.domain.Client;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.IClientService;
import ru.komiparma.manifest.service.IImportMega;
import ru.komiparma.manifest.service.IWayBillService;


@Component
@ViewScoped
public class View_Import {
	
	private Date startDate;
	private Date endDate;
	private String nmbr;
	private List<WayBill> data;
	
	@Autowired
	private IImportMega megaSrv;
	@Autowired
	private IWayBillService wbSrv;
	@Autowired
	private IClientService wbClnt;
	
	public View_Import(){
		startDate=Calendar.getInstance().getTime();
		endDate=Calendar.getInstance().getTime();
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getNmbr() {
		return nmbr;
	}
	public void setNmbr(String nmbr) {
		this.nmbr = nmbr;
	}
	public List<WayBill> getData() {
		return data;
	}
	public void setData(List<WayBill> data) {
		this.data = data;
	}
	public void onImport(ActionEvent e){
		System.out.println("Click");
		Calendar stCal = Calendar.getInstance();
		stCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		data=megaSrv.importWayBills(stCal, endCal);
		System.out.println("Complete import, have :"+data.size()+" waybills");
	}
	
	public void onStop(ActionEvent e){
		this.megaSrv.stop();
	}
	
	public void onSaveToBase(ActionEvent e){
		//this.megaSrv.saveToBase(data);
		System.out.println(this.wbSrv.getAll().size());
	}
	
	public void onUpdate(ActionEvent e){
		this.megaSrv.updateWayBill(this.nmbr);
	}
	public void onTest(ActionEvent e){
		WayBill wb = new WayBill();
		wb.setTitle("123456789");
		wb.setRecipient_fio("Иванов И.И.");
		wb.setCounts(5);
		wb.setWeight(2.2);
		Client cl = new Client("Тест", "Славы 4, 164", null, "124414");
		wb.setRecipient(cl);
		this.wbClnt.save(cl);
		wb.setSender(null);
		this.wbSrv.save(wb);
	}
	public void onToggle(ToggleEvent e){
		
	}
	
}
