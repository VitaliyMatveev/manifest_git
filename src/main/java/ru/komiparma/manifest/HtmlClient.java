package ru.komiparma.manifest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HtmlClient  {
	private static final Logger log = Logger.getLogger(HtmlClient.class);
	private WebClient webClient;
	
	public HtmlClient() {
		webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
	}
	/**
	 * ѕроизводит аунтефикацию на сайте.
	 * »спользует в качетсве параметров пароль и логин, 
	 * находит соответвующие элементы по Name и вставл€ет значени€
	 * Ќаходит ссылку по содержанию и производит логин посредством .click()
	 * @param pas
	 * @param login
	 */
	
	public void login(String pas, String login){
		try {
			log.info("Try loggin to syte as "+login);
			HtmlPage loginPage = webClient.getPage("http://mega.me-online.ru/Login.asp");
			HtmlForm loginForm=loginPage.getFormByName("enter");		
			HtmlTextInput loginInput = loginForm.getInputByName("Login");
			loginInput.setValueAttribute(login);	
			HtmlPasswordInput passInput = loginForm.getInputByName("PWD");
			passInput.setValueAttribute(pas);
			loginPage = loginPage.getAnchorByHref("javascript:document.enter.submit()").click();
			log.info("Succes loggin to syte as "+login);
		} catch (ElementNotFoundException e) {
			log.error("Not found element ",e);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		
	}
	
	public void close(){
		webClient.closeAllWindows();
		log.info("Close all windows");
	}
	
	public List<HtmlPage> readWayBillPage(String wbNumber) {
		try {
			log.info("Try read waybill "+wbNumber+" page");
			if(wbNumber.contains("/")){
				wbNumber=wbNumber.substring(0, wbNumber.indexOf('/'));
				log.info("Number contains '/', normal title for href "+wbNumber);
			}
			String mainUrl = "http://mega.me-online.ru/Invoice.Edit.asp?WBNumber="+wbNumber;
			log.info("Url to open "+mainUrl);
			String historyUrl = "http://mega.me-online.ru/InvoiceHistory.asp?WBNumber="+wbNumber;
			List<HtmlPage> result = new ArrayList<HtmlPage>();
			HtmlPage wayBillMainPage = webClient.getPage(mainUrl);
			HtmlPage wayBillHistoryPage;	
			wayBillHistoryPage = webClient.getPage(historyUrl);
			result.add(wayBillMainPage);
			result.add(wayBillHistoryPage);
			log.info("Compleat read pages for "+wbNumber);
			return result;
			
		} catch (FailingHttpStatusCodeException e) {
			log.error("Fail while try log on page", e);
			if(e.getStatusCode()==500){
				try {
					log.info("wait five seconds and try again");
					Thread.sleep(5000);
					log.info("Restart method 'readWayBillPage'");
					this.readWayBillPage(wbNumber);
				} catch (InterruptedException e1) {
					log.error(e1.getLocalizedMessage(), e1);
				}
			}
			log.error(e.getLocalizedMessage(),e);
		} catch (MalformedURLException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return null;
	}
	
	public List<String> getWayBillList(Calendar startDate,Calendar endDate){
		try {
			log.info("Try read waybill numbers for interval from "+startDate+" to "+endDate);
			List <String> result = new ArrayList<String>();
			
			HtmlPage wayBillPage = webClient.getPage("http://mega.me-online.ru/Invoice.asp");
			HtmlInput calendarStartDate = wayBillPage.getElementByName("WBOpenDate");
			HtmlInput calendarEndDate = wayBillPage.getElementByName("WBCloseDate");
			HtmlButtonInput button = wayBillPage.getElementByName("apply");
			
			
			DateFormat dateFormatForSyte = new SimpleDateFormat("dd.MM.yyyy");
			calendarStartDate.setValueAttribute(dateFormatForSyte.format(startDate.getTime()));
			calendarEndDate.setValueAttribute(dateFormatForSyte.format(endDate.getTime()));
			HtmlPage resultPage = button.click();
			log.info("Open html page with waybills");
			while(true){
					for(HtmlAnchor a:resultPage.getAnchors()){
						if(a.getHrefAttribute().contains("WBNumber")){
							result.add(a.asText());
						}
					}
				try{	
					resultPage=resultPage.getAnchorByText("—ледующа€").click();
					log.info("Open next page with waybills");
				} catch (ElementNotFoundException e){
					log.info("Compleat search waybill pages, no more anchor with text 'слудующа€'");
					break;
				}
			}
			if(log.isDebugEnabled()){
				log.debug("Size results "+result.size());
				for(String s:result){
					log.debug(s);
				}
			}
			return result;
	
		} catch (FailingHttpStatusCodeException e) {
			log.error("Error open page while search all waybill numbers", e);
		} catch (MalformedURLException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return null;
	}
	
}

