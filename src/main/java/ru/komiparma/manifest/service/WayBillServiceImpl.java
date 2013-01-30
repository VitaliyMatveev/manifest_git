package ru.komiparma.manifest.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.komiparma.manifest.dao.IWayBillDAO;
import ru.komiparma.manifest.domain.WayBill;

@Repository
public class WayBillServiceImpl extends GenericServiceImpl<WayBill> implements IWayBillService{
	private IWayBillDAO wbDAO;
	@Autowired	
	public WayBillServiceImpl(IWayBillDAO wayBillDAO){
		super(wayBillDAO);
		this.wbDAO=wayBillDAO;
	}
	
	@Transactional
	public List<WayBill> getWBNumberFromDiaposon(Date start,Date end){
		return wbDAO.getWBNumbersForDiaposon(start, end);
	}
	
	@Transactional
	public List<WayBill> getWayBillByCity(String city){
		
		return null;
	}
	
	@Transactional
	public WayBill get(String title){
		return this.wbDAO.get(title);
	}
}
