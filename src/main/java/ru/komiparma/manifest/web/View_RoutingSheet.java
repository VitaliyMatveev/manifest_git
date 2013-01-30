package ru.komiparma.manifest.web;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import net.sf.jasperreports.engine.JRException;


import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.Client;
import ru.komiparma.manifest.domain.Courier;
import ru.komiparma.manifest.domain.RoutingSheet;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.ICourierService;
import ru.komiparma.manifest.service.IRoutingSheetService;
import ru.komiparma.manifest.service.IWayBillService;

@Component
@Scope("session")
public class View_RoutingSheet  implements Serializable{
	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(View_RoutingSheet.class);
	private static final long serialVersionUID = 5452097384362567138L;
	private Integer courierId;
	private List<Courier> courierList;
	private String numberWB;
	private IWayBillService wbSrv;
	private ICourierService crSrv;
	private IRoutingSheetService rsSrv;
	private WayBill selectWB;
	private List<RoutingSheet> lastRoutingSheet;
	private RoutingSheet rs;
	private String file;
	//TODO todel
	private WayBill wb;
	
	@Autowired
	public View_RoutingSheet(IWayBillService wbSrv, ICourierService crSrv, IRoutingSheetService rsSrv){
		this.wbSrv=wbSrv;
		this.crSrv=crSrv;
		this.rsSrv=rsSrv;
		lastRoutingSheet=this.rsSrv.getLastUseRS();
		rs = new RoutingSheet();
		log.info("==================================");
		log.info("Create Routing Sheet View");
		log.info("==================================");
		rs.setDateCreated(Calendar.getInstance().getTime());
		this.rs.setTitle(""+this.rsSrv.getAll().size());
		
	}
		
		public String getFile() {
			System.out.println("Get "+file);
			return file;
		}

		public WayBill getWb() {
			return wb;
		}

		public void prepere(){
			wb = new WayBill();
			Client recipient = new Client();
			wb.setRecipient(recipient);
		}
		public void addNewWayBill(){
			if(!(wb.getTitle().isEmpty())){
				this.wbSrv.save(wb);
				this.rs.addWayBill(wb);
			}
		}

	public RoutingSheet getRs() {
		return rs;
	}

	public Integer getCourierId() {
		if(rs.getCourier()!=null)
			return rs.getCourier().getId();
		else return courierId;
	}
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
		if(courierId!=null){
			this.rs.setCourier(this.crSrv.get(courierId));
			log.info("In RoutingSheet "+rs.getId()+":"+rs.getTitle()+" set courier "+rs.getCourier().getId()+":"+rs.getCourier().getFio());
		}
	}
	public List<Courier> getCourierList() {
		courierList=crSrv.getAll();
		return courierList;
	}
	
	
	public Date getDate() {
		return rs.getDateCreated();
	}


	public void setDate(Date date) {
		log.info("try set Date "+date);	
		if(date!=null){
			this.rs.setDateCreated(date);
			log.info("RoutingSheet "+rs.getId()+" set Date "+date);	
		}
	}


	public void setCourierList(List<Courier> courierList) {
		this.courierList = courierList;
	}
	
	public String getNumberWB() {
		return numberWB;
	}

	public void setNumberWB(String numberWB) {
		this.numberWB = numberWB;
	}

	
	public WayBill getSelectWB() {
		return selectWB;
	}

	public void setSelectWB(WayBill selectWB) {
		log.info("waybill "+selectWB+" selected");
		this.selectWB = selectWB;
	}

	
	public List<RoutingSheet> getLastRoutingSheet() {
		this.lastRoutingSheet=this.rsSrv.getLastUseRS();
		return lastRoutingSheet;
	}
	
	public void setLastRoutingSheet(List<RoutingSheet> lastRoutingSheet) {
		this.lastRoutingSheet = lastRoutingSheet;
	}

	public void doAdd(){
		try{
			log.info("Try add WayBill with number "+numberWB);
			if(numberWB.length()>3){
				WayBill wb = this.wbSrv.get(numberWB);
				if(wb!=null){
					if(rs.getWayBillList().contains(wb)){
						printError("Дубликат накладной в доставочном листе");
						this.numberWB="";
					} else {
						rs.addWayBill(wb);
						log.info("Succes search and add WayBill to RoutingSheet '"+this.rs.getId()+":"+this.rs.getTitle()+"'");
						this.numberWB="";
					}
				}
			else {
				log.error("WayBill " +numberWB +  " not found in database");
				printError("Не найдено накладной с номером: '"+this.numberWB+"'");
				}
			}
		} catch(NonUniqueResultException e){
			log.error("WayBill " +numberWB,e);
			printError("Найдено несколько совпадений для накладной: '"+this.numberWB+"' , уточните номер!");
		}
	}
	public void changePos(String k){
		int i = rs.getWayBillList().indexOf(selectWB);
		int j;
		if (k.equals("up")) 
				j = i-1;
			else
				j= i +1;
		try{
			WayBill wb = rs.getWayBillList().get(j);
			rs.getWayBillList().set(j,this.selectWB);
			rs.getWayBillList().set(i, wb);
		} catch (IndexOutOfBoundsException e){
			printError("Достигнут предел списка");
		}
	}
	
	public void doExport() throws IOException{
		try {
			log.info("export rs "+rs.getId()+":"+rs.getTitle());
			if(rs.getCourier().getId()==null){
				throw new IllegalArgumentException();
			}
			this.rsSrv.save(rs);
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.setResponseContentType("application/pdf");
			this.rsSrv.exportToPdf(rs,externalContext.getResponseOutputStream());
			context.responseComplete();
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			log.error("Can't export to PDF, file error",e);
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			log.error("Courier is not selected");
			printError("Выберите курьера");
		} catch (NullPointerException e){
			printError(e.getMessage());
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	public void doRemove(){
		log.info("Try delete waybill "+selectWB+" from routingsheet "+rs.getTitle());
		if(selectWB!=null){
			rs.getWayBillList().remove(selectWB);
			//this.rsSrv.removeWayBillFromCollection(rs, selectWB);
			log.info("Succes delete from list");
		}
		
	}

	/*
	 * Method to work with RoutingSheet
	 */
	public void saveRoutingSheet(){
		log.info("Save routing sheet "+rs.getId()+":"+rs.getTitle());
		this.rsSrv.save(rs);
	}
	
	public void openRoutingSheet(String id){
		int index = Integer.parseInt(id);
		this.rs=lastRoutingSheet.get(index);
	}
	
	public void newRoutingSheet(ActionEvent e){
		this.rs=new RoutingSheet();
		this.rs.setTitle(""+this.rsSrv.getAll().size());
		this.courierId=null;
	}
	
	
	private void openFile(File f){
		if(f!=null){
			Desktop des = null;
			if(Desktop.isDesktopSupported()){
				des = Desktop.getDesktop();
				try{
					des.open(f);
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	private void printError(String msg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
		log.info("succes create message "+msg);
	}
	
}
