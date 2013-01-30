package ru.komiparma.manifest.dao;

import java.sql.Date;
import java.util.List;

import ru.komiparma.manifest.domain.WayBill;

public interface IWayBillDAO extends IGenericDAO<WayBill> {
	
	public List<WayBill> getWBNumbersForDiaposon(Date stDate,Date endDate);
	
	public WayBill get(String title);
}
