package ru.komiparma.manifest.dao;

import ru.komiparma.manifest.domain.City;

public interface ICityDAO extends IGenericDAO<City>{
	public City getCityByTitle(String title);
}
