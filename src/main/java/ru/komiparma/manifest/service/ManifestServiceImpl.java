package ru.komiparma.manifest.service;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.komiparma.manifest.dao.IManifestDAO;
import ru.komiparma.manifest.dao.IWayBillDAO;
import ru.komiparma.manifest.domain.Manifest;

@Repository
public class ManifestServiceImpl extends GenericServiceImpl<Manifest> implements IManifestService{
private IManifestDAO manDAO;	
@Autowired
	public ManifestServiceImpl(IManifestDAO manDAO,IWayBillDAO wbDAO,SessionFactory sessionFactory){
		super(manDAO);		
		this.manDAO=manDAO;
	}

@Override
@Transactional
public List<Manifest> getManifestPeriodDate(String city, Date date) {
	return this.manDAO.getManifestPeriodDate(city, date);
}

}
