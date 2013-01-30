package ru.komiparma.manifest.dao;


import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.domain.Courier;
@Repository
public class CourierDAOImpl extends GenericDAOImpl<Courier> implements ICourierDAO {
	
	public CourierDAOImpl(){
		super(Courier.class);
	}

}
