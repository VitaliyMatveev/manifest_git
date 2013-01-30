package ru.komiparma.manifest.test;


import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import ru.komiparma.manifest.dao.ICityDAO;
import ru.komiparma.manifest.dao.ICourierDAO;
import ru.komiparma.manifest.dao.IGenericDAO;
import ru.komiparma.manifest.dao.IManifestDAO;
import ru.komiparma.manifest.dao.IRoutingSheetDAO;
import ru.komiparma.manifest.dao.IWayBillDAO;
import ru.komiparma.manifest.domain.City;
import ru.komiparma.manifest.domain.Courier;
import ru.komiparma.manifest.domain.Manifest;
import ru.komiparma.manifest.domain.RoutingSheet;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.ICityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({"/WEB-INF/applicationContext.xml"})
public class Generic_DAO_test {
	
	@Test
	public void TestWayBill(){
		WayBill wb = new WayBill();
		wb.setDateCreate("01.01.2011");
		System.out.println(wb.getDateCreate());
	}
}