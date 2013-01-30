package ru.komiparma.manifest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class HtmlParser {
	private static final Logger log = Logger.getLogger(HtmlParser.class);
	
	private HtmlPage page;
	
	public HtmlParser(){
		
	}
	
	public Map<String, String> parseWayBillMain(String nmbr,HtmlPage page){
		this.page=page;
		log.info("Start parse waybill page "+page.getUrl());
		Map <String,String> results = new HashMap<String, String>();
		//Main
		results.put("number", nmbr);
		//results.put("fedexNumber", page.getElementByName("invoice2").asText());
		try{
			results.put("comments",page.getElementByName("comments").asText()+" "+page.getElementByName("commentsadd").asText()+" "+page.getElementByName("readdress").asText());
			results.put("payerCode", page.getElementByName("payerCode").asText());
		//	System.out.println("Comments: "+results.get("comments"));
			/* Инфа по платильщику
			results.put("client", page.getElementByName("clientList").asText());
			results.put("payerCode", page.getElementByName("payerCode").asText());
			results.put("payer", page.getElementByName("payer").asText());
			*/
			
			//Sender
			results.put("sfio", page.getElementByName("sfio").asText());
			results.put("sphone", page.getElementByName("sphone").asText());
			results.put("scompany", page.getElementByName("scompany").asText());
			results.put("scountry", page.getElementByName("scountry").asText());
			results.put("scity", page.getElementByName("scity").asText());
			results.put("sagent", page.getElementByName("sagent").asText());
			results.put("saddress", page.getElementByName("saddress").asText());
			
			//Recipient
			results.put("rfio", page.getElementByName("rfio").asText());
			results.put("rphone", page.getElementByName("rphone").asText());
			results.put("rcompany", page.getElementByName("rcompany").asText());
			results.put("rcountry", page.getElementByName("rcountry").asText());
			results.put("rcity", page.getElementByName("rcity").asText());
			results.put("ragent", page.getElementByName("ragent").asText());
			results.put("raddress", page.getElementByName("raddress").asText());
			
			//Attribute
			results.put("weight", page.getElementByName("weight").asText());
			results.put("volume", page.getElementByName("volumeweight").asText());
			results.put("amount", page.getElementByName("amount").asText());
			results.put("PayType", whoCheked("PayType"));
			results.put("WWPay", whoCheked("WWPay"));
			
			
			//About
			results.put("WBOpenDate", page.getElementByName("WBOpenDate").asText());
			results.put("direckTransport", whoCheked("IsTransit"));
			results.put("insurance", ((HtmlElement)page.getByXPath("/html/body/center/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr[9]/td/table/tbody/tr/td[1]/fieldset/table/tbody/tr[2]/td[3]/input").get(0)).asText());
			results.put("transportation", ((HtmlElement)page.getByXPath("/html/body/center/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr[9]/td/table/tbody/tr/td[1]/fieldset/table/tbody/tr[3]/td[3]/input").get(0)).asText());
			results.put("cargoDescription", page.getElementByName("description").asText());
			results.put("DeliveryConditions", page.getElementByName("DeliveryConditions").asText());
			
		} catch (ElementNotFoundException e) {
			log.error("Error while parse HTML page "+page.getUrl()+ " NOT FOUND ELEMENT", e);
		}
		
		page.cleanUp();
		if(log.isDebugEnabled()){
			log.debug("Html page "+page.getUrl()+" contains:");
			for(String s:results.keySet()){
				log.debug(s+": "+results.get(s));
			}
		}		
		return results;
	}
	
	public Map<Integer, List<String>> parseWayBillHistory(HtmlPage page){
		//System.out.println(page.asText());

		Map<Integer,List<String>>events = new HashMap<Integer, List<String>>();
		HtmlTable tableWithHistory = (HtmlTable)page.getByXPath("/html/body/center/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td/table").get(0);
		Integer num = 0;
		for(HtmlTableRow row:tableWithHistory.getRows()){
			if(row.getIndex()!=0){
				List<String> event = new ArrayList<String>();
				for(HtmlTableCell cell:row.getCells()){
					if(cell.getIndex()!=0)
						event.add(cell.asText());
				}
				events.put(num++, event);
			}
		}
		
		page.cleanUp();
		System.out.println("Events size: "+events.size());
		/*for(Integer event : events.keySet()){
			System.out.print(event+": ");
			for(String s:events.get(event)){
				System.out.print(s+" ");
			}
			System.out.println("");
		}
		*/
		return events;
	}
	
	/**
	 * Search elements with @param name
	 * detect what's button is checked
	 * take parent element 
	 * and @return last child (description of radio button)
	 */
	private String whoCheked(String name){
		for(HtmlElement el:page.getElementsByName(name)){
			HtmlRadioButtonInput btn = (HtmlRadioButtonInput)el;
			if(btn.isChecked())
				return el.getParentNode().getLastChild().asText();
		}
		return null;
	}
	
}
