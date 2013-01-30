package ru.komiparma.manifest.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Component;

@Component
@ApplicationScoped
public class Utilities {
	private List <SelectItem> listTypeOfTransport;
	
	public Utilities(){
		listTypeOfTransport=new ArrayList<SelectItem>();
		listTypeOfTransport.add( new SelectItem("auto", "Автотранспорт"));
		listTypeOfTransport.add( new SelectItem("avio", "Авиарейс"));
		listTypeOfTransport.add( new SelectItem("train", "Багажный вагон"));
	}
	
	public List<SelectItem> getListTypeOfTransport() {
		return listTypeOfTransport;
	}

	public void setListTypeOfTransport(List<SelectItem> listTypeOfTransport) {
		this.listTypeOfTransport = listTypeOfTransport;
	}
}
