package ru.komiparma.manifest.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.komiparma.manifest.domain.City;
import ru.komiparma.manifest.domain.Client;
import ru.komiparma.manifest.domain.Courier;
import ru.komiparma.manifest.domain.Manifest;
import ru.komiparma.manifest.domain.RoutingSheet;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.ICityService;
import ru.komiparma.manifest.service.IClientService;
import ru.komiparma.manifest.service.IManifestService;
import ru.komiparma.manifest.service.IRoutingSheetService;
import ru.komiparma.manifest.service.IWayBillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ("./WEB-INF/applicationContext.xml")
public class Generic_Service_test {
	@Autowired
	private ICityService citySrv;
	@Autowired
	private IManifestService mnfSrv;
	@Autowired
	private IClientService clntSrv;
	@Autowired
	private IWayBillService wbSrv;
	@Autowired
	private IRoutingSheetService rsSrv;
	
	public void main() throws IOException{
		testCompareted();
	} 
	
	@Test
	public void testRS(){
		this.wbSrv.removeAll();
	}

	
	public void testExport(){
		try {
			List<WayBill> list = this.wbSrv.getAll();
			Map<String, Object> params = new HashMap<String, Object>();
			String title = "1234";
			String report = "routsheet_"+title+".pdf";
			String template = "report.jasper";
			params.put("RS_TITLE", title);
			params.put("RS_COURIER","Иванов И.И.");
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			params.put("RS_DATE", df.format(Calendar.getInstance().getTime()));
			JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReport(template, params, new JRBeanCollectionDataSource(this.wbSrv.getAll())),report);
			Desktop des = null;
			if(Desktop.isDesktopSupported()){
				des = Desktop.getDesktop();
				try{
					des.open(new File(report));
				} catch (IOException e){
					e.printStackTrace();
				}
			}
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createDB(){
		City c1 = this.citySrv.get("Ухта");
		Manifest m1 = new Manifest("12345",Calendar.getInstance().getTime());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -4);
		Date d = cal.getTime();
		Manifest m2 = new Manifest("567",d);
		cal.add(Calendar.MONTH, -10);
		Date d2 = cal.getTime();
		Manifest m3 = new Manifest("mnbd",d2);
		
		c1.addManifest(m1);
		c1.addManifest(m2);
		c1.addManifest(m3);
		this.citySrv.save(c1);
		
	/*	City c1 = new City("Ухта");
		City c2 = new City("Воркута");
		City c3 = new City("Печора");
		City c4 = new City("Инта");
		City c5 = new City("Усинск");
		this.citySrv.save(c1);
		this.citySrv.save(c2);
		this.citySrv.save(c3);
		this.citySrv.save(c4);
		this.citySrv.save(c5);*/
	}
	public void testWayBill(){
		System.out.println(this.wbSrv.get("66004214").getTitle());
	}
	
	public void testManifest(){
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date date;
		try {
			date = df.parse("01.07.2012");
			assertNotNull(date);
			System.out.println(date);
			List<Manifest> list = this.mnfSrv.getManifestPeriodDate("Ухта", date);
			System.out.println(list.size());
			for(Manifest mnf:list){
				System.out.println(mnf.getTitle()+" "+mnf.getCity()+" "+mnf.getDateRecive());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testCompareted(){
		List<WayBill> wbList = this.wbSrv.getAll();
		WayBill wb1 = wbList.get(1);
		WayBill wb2 = wbList.get(2);
		System.out.print(wb1.getTitle()+" "+wb2.getTitle()+" "+wb1.getTitle().compareTo(wb2.getTitle()));
		
	}

	public void getClient(){
		List<Client> clntList = new ArrayList();
		for(Client c:clntSrv.getAll()){
			if(c.getCity().toLowerCase().contains("сыктывкар")){
				clntList.add(c);
			}
		}
		Map<String, List<String>> clients= new HashedMap();
		for(Client c:clntList){
			if(clients.get(getAdr(c))!=null){
				if(!(clients.get(getAdr(c)).contains(c.getCompany())))
					clients.get(getAdr(c)).add(c.getCompany());
			} else {
				List<String> list =new ArrayList<String>();
				list.add(c.getCompany());
				clients.put(getAdr(c),list);
			}
		}
		System.out.println(clients.size());
		for(String key:clients.keySet()){
			System.out.println(key+" "+clients.get(key));
		}
		
	}
	public String getAdr(Client c){
		String str;
		if((str=findStreet(c.getAddress(), getListStreet()))!=null){
			str=str+" ";
			str=str + findHouseNumber(c.getAddress());
			return str;
		} else {
			return null;
		//	System.out.println(++i+": НЕРАСПОЗНАНО "+address);
		}
	}
	
	private String parseNumByRegex(String str){
		Pattern p2 = Pattern.compile("\\d+[\\/\\\\]?\\d*([а-яА-Я])?");
		Matcher m2 =p2.matcher(str);
		if(m2.find())
			return m2.group();
		else 
			return null;
	}
	
	public String findHouseNumber(String address){
		Pattern p = Pattern.compile("д(ом)?\\.?\\s?(\\d+[\\/\\\\]?\\d*([а-яА-Я])?)");
		Matcher m =p.matcher(address);
			
		//System.out.println(address);
		String nmb;
		if(m.find()){
			nmb=m.group();
			nmb=parseNumByRegex(nmb);
		} else {
			nmb=parseNumByRegex(address);
		}
		
		return nmb;
	}
	
	private List<String> getAddressList(){
		List<String> listAddress = new ArrayList<String>();
		for(Client c:clntSrv.getAll()){
			if((c.getCity().toLowerCase()).contains("сыктывкар"))
					listAddress.add(c.getAddress());
		}
		return listAddress;
	}
	
	private Set<String> getListStreet(){
		try {
			DataInputStream myfile = new DataInputStream( new FileInputStream("D:/street.txt"));
			BufferedReader reader  = new BufferedReader(new InputStreamReader(myfile));
			String str;
			Set<String> listAdr=new HashSet<String>();
			while((str=reader.readLine())!=null){
				listAdr.add((str.toLowerCase()).trim());
			}
			return listAdr;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	public String findStreet(String address,Set<String> listAdr){
		for(String sourAddr:listAdr){
			if((address.toLowerCase()).contains(sourAddr)){
				return sourAddr;
			}
		}
		return null;
	}
		
}
