package ru.komiparma.manifest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.dao.ICourierDAO;
import ru.komiparma.manifest.domain.Courier;

@Repository
public class CourierServiceImpl extends GenericServiceImpl<Courier> implements ICourierService {
	@Autowired	
	public CourierServiceImpl(ICourierDAO courierDAO){
		super(courierDAO);
	}
}
