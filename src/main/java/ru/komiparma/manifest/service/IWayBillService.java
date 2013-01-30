package ru.komiparma.manifest.service;

import java.sql.Date;
import java.util.List;

import ru.komiparma.manifest.domain.WayBill;

public interface IWayBillService extends IGenericService<WayBill>{
	
	public List<WayBill> getWBNumberFromDiaposon(Date start,Date end);
	
	public WayBill get(String title);

}
