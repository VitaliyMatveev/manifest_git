package ru.komiparma.manifest.service;

import java.util.Calendar;
import java.util.List;
import ru.komiparma.manifest.WayBillFromMega;
import ru.komiparma.manifest.domain.WayBill;


public interface IImportMega {
	public WayBill updateWayBill(String nmbr);
	
	public List<WayBill> updateWayBills(List<String> nmbrs);

	public List<WayBill> importWayBills(Calendar startDate, Calendar endDate);
	
	public void saveToBase(List<WayBillFromMega> listOfWayBills);
	
	public void deilyImport();
	
	public void stop();
}
