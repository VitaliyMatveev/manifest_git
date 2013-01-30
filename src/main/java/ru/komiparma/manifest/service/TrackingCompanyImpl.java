package ru.komiparma.manifest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.dao.ITrackingCompanyDAO;
import ru.komiparma.manifest.domain.TrackingCompany;

@Repository
public class TrackingCompanyImpl extends GenericServiceImpl<TrackingCompany> implements ITrackingCompanyService{

	@Autowired
	public TrackingCompanyImpl(ITrackingCompanyDAO trackCompDAO){
		super(trackCompDAO);
	}
}
