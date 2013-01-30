package ru.komiparma.manifest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.komiparma.manifest.dao.ICityDAO;
import ru.komiparma.manifest.domain.City;

@Repository
public class CityServiceImpl extends GenericServiceImpl<City> implements ICityService {
	private ICityDAO cityDAO;
	@Autowired
	public CityServiceImpl(ICityDAO cityDAO){
		super(cityDAO);
		this.cityDAO=cityDAO;
	}

	@Transactional
	public City get(String title) {
		if(title!=null){
			City city=this.cityDAO.getCityByTitle(title);
			return city;
		} else {
			return null;
		}
	}
}
