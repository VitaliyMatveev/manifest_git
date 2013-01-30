package ru.komiparma.manifest.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ru.komiparma.manifest.HistoryOfWayBillFromMega;
import ru.komiparma.manifest.HtmlClient;
import ru.komiparma.manifest.HtmlParser;
import ru.komiparma.manifest.WayBillFromMega;
import ru.komiparma.manifest.domain.Client;
import ru.komiparma.manifest.domain.WayBill;

@Repository
public class ImportMegaImpl implements IImportMega {
	private static final Logger log = Logger.getLogger(ImportMegaImpl.class);
	
	HtmlClient client;
	HtmlParser parser;
	
	private IWayBillService wbSrv;
	private IClientService clntSrv;
	
	private boolean stop = false;
	
	@Autowired
	public ImportMegaImpl(IWayBillService wbSrv,IClientService clntSrv){
			client = new HtmlClient();
			parser = new HtmlParser();
			this.wbSrv=wbSrv;
			this.clntSrv=clntSrv;
			log.info("=============================");
			log.info("Create ImportMegaImpl");
			log.info("=============================");
	}
	
	@Override
	public WayBill updateWayBill(String nmbr) {
			if(nmbr!=null){
				client.login("pwdfLwerGVFdE", "lsiktiv");
				List<HtmlPage> pages= client.readWayBillPage(nmbr);
				WayBill wb=createAndSave(parser.parseWayBillMain(nmbr,pages.get(0)));
				client.close();
				return wb;
			}
		return null;
	}

	@Override
	public List<WayBill> importWayBills(Calendar startDate,Calendar endDate) {
		client.login("pwdfLwerGVFdE", "lsiktiv");
		List<WayBill> results = new ArrayList<WayBill>();
		List<String> listWB=client.getWayBillList(startDate, endDate);
		List<HtmlPage> pages;
		log.info("Start import "+listWB.size());
		listWB=checkForMatches(startDate, endDate, listWB);
		log.info("Ready to import "+listWB.size());
		for(String nmbr:listWB){
			pages = client.readWayBillPage(nmbr);
			results.add(createAndSave(parser.parseWayBillMain(nmbr, pages.get(0))));
		}
		this.client.close();
		return results;
	}

	@Override
	public List<WayBill> updateWayBills(List<String> nmbrs) {
		List<WayBill> results = new ArrayList<WayBill>();
		client.login("pwdfLwerGVFdE", "lsiktiv");
		List<HtmlPage> pages;
		for(String nmbr : nmbrs){
			pages = client.readWayBillPage(nmbr);
			results.add(createAndSave(parser.parseWayBillMain(nmbr, pages.get(0))));
		}
		return results;
	}
	@Override
	public void saveToBase(List<WayBillFromMega> listOfWayBills) {
		
		
	}
	
	
	private List<String> checkForMatches(Calendar startCal,Calendar endCal,List<String> source){
		Date startDate = new Date(startCal.getTime().getTime());
		Date endDate = new Date(endCal.getTime().getTime());
		List<String> wbNumbersFromDB = new ArrayList<String>();
		for(WayBill wb:this.wbSrv.getWBNumberFromDiaposon(startDate, endDate)){
			wbNumbersFromDB.add(wb.getTitle());
		}
		source.removeAll(wbNumbersFromDB);
		log.info("Database have "+wbNumbersFromDB.size()+" waybills on diaposon date from "+startDate+" to "+endDate);
		if(log.isDebugEnabled()){
			log.debug("Database have next waybill to diaposone:");
			for(String title:wbNumbersFromDB){
				log.debug(title);
			}
		}
		startDate=null;
		endDate=null;
		wbNumbersFromDB=null;
		
		return source;
	}
	
	private WayBill createAndSave(Map<String, String> map){
		WayBill wb = new WayBill();
		//�����
		wb.setTitle(map.get("number"));
		//Fedex ����� ���� ����
		//wb.setFedex(map.get("fedexNumber"));
		//����� �����������
		wb.setKod(map.get("payerCode"));
		//�����������
		//wb.setComments(map.get("comments"));
		//��� �����������
		wb.setSender_fio(map.get("sfio"));
		//��� ����������
		wb.setRecipient_fio(map.get("rfio"));
		//���
		wb.setWeight(map.get("weight"));
		//�������� ���
		wb.setVolume(map.get("volume"));
		//����������
		wb.setCounts(map.get("amount"));
		//��� ������ (���/����)
		wb.setPayType(map.get("PayType"));
		//����������(����������/�����������/3-� �������)
		wb.setWhoWillPay(map.get("WWPay"));
		//���� ��������
		wb.setDateCreate(map.get("WBOpenDate"));
		//�������� �����
		wb.setCargoDescription(map.get("cargoDescription"));
		//������ ������� ��������(�������/������/)
		wb.setSpecialConditions(map.get("DeliveryConditions"));
		//��� ���������(������/������)
		wb.setDirectTransport(map.get("direckTransport"));
		//����� �� ���������
		wb.setInsurance(map.get("insurance"));
		//����� �� ���������
		wb.setTransportation(map.get("transportation"));
		
		//�����������
		Client sender = new Client(map.get("scompany"),
									map.get("saddress"),
									map.get("scity"),
									map.get("sphone"));
		//����������
		Client recipient = new Client(map.get("rcompany"),
									map.get("raddress"),
									map.get("rcity"),
									map.get("rphone"));
		
		wb.setSender(sender);
		wb.setRecipient(recipient);
		
		this.clntSrv.save(sender);
		this.clntSrv.save(recipient);
		this.wbSrv.save(wb);
		
		return wb;
	}
	
	private List<HistoryOfWayBillFromMega> formatHistory(Map<Integer,List<String>> historyMap){
		List<HistoryOfWayBillFromMega> results= new ArrayList<HistoryOfWayBillFromMega>();
			for(List<String> list : historyMap.values()){
				if(list.isEmpty()) continue;
				results.add(new HistoryOfWayBillFromMega(list.get(1),list.get(2),list.get(3),list.get(4)));
			}
		return results;
		
	}

	@Override
	public void stop() {
		stop=true;
		client.close();
		
	}

	

	public void deilyImport() {
		this.importWayBills(Calendar.getInstance(), Calendar.getInstance());
	}

}
