package ru.komiparma.manifest.dao;

import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.domain.TrackingCompany;

@Repository
public class TrackingCompanyDAOImpl extends GenericDAOImpl<TrackingCompany> implements ITrackingCompanyDAO{
	
	public TrackingCompanyDAOImpl(){
		super(TrackingCompany.class);
	}
}
