package ru.komiparma.manifest.web;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.Courier;
import ru.komiparma.manifest.service.ICourierService;

@Component
@Scope("request")
public class View_Dictionary {
	private List<Courier> data;
	private Courier selected;
	private String fio;
	
	@Autowired
	private ICourierService crSrv;
	
	public List<Courier> getData() {
		data=this.crSrv.getAll();
		return data;
	}
	public void setData(List<Courier> data) {
		this.data = data;
	}
	public Courier getSelected() {
		return selected;
	}
	public void setSelected(Courier selected) {
		this.selected = selected;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public void add(){
		Courier cr = new Courier();
		cr.setFio(fio);
		this.fio="";
		this.crSrv.save(cr);
		
	}
	
	public void del(){
		crSrv.remove(selected);
	}
	

}
