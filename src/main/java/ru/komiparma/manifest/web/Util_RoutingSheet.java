package ru.komiparma.manifest.web;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.komiparma.manifest.domain.RoutingSheet;
import ru.komiparma.manifest.service.IRoutingSheetService;

@Component
@Scope("request")
public class Util_RoutingSheet {
	@Autowired
	private IRoutingSheetService rsSrv;
	
	private List<RoutingSheet> lastRoutingSheet;
	private MenuModel simpleMenuModel = new DefaultMenuModel();
	
	public Util_RoutingSheet(){
		System.out.println("create Util_RoutingSHeet");
	}
	public List<RoutingSheet> getLastRoutingSheet() {
		return rsSrv.getLastUseRS();
	}

	
	public MenuModel getSimpleMenuModel() {
		System.out.println("get SimpleMenuModel");
		formatMenuModel();
		return simpleMenuModel;
	}


	private void formatMenuModel(){
		for(RoutingSheet rs:this.getLastRoutingSheet()){
		    MenuItem menuItem = new MenuItem();
	        menuItem.setValue(rs.getTitle());
	        menuItem.setUrl("#");
	        simpleMenuModel.addMenuItem(menuItem);
		}
	}
	
	
	
}
