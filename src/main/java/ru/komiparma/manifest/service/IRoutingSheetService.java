package ru.komiparma.manifest.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import ru.komiparma.manifest.domain.RoutingSheet;

public interface IRoutingSheetService extends IGenericService<RoutingSheet>{

	public void exportToPdf(RoutingSheet rs, OutputStream stream) throws FileNotFoundException, JRException,IOException;
	
	public List<RoutingSheet> getLastUseRS();
	
	//public boolean removeWayBillFromCollection(RoutingSheet rs,WayBill wbToRemove);
}
