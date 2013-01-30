package ru.komiparma.manifest.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.domain.RoutingSheet;

@Repository
public class RoutingSheetDAOImpl extends GenericDAOImpl<RoutingSheet> implements IRoutingSheetDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public RoutingSheetDAOImpl(){
		super(RoutingSheet.class);
	}
}
