package ru.komiparma.manifest.service;

import ru.komiparma.manifest.domain.City;

public interface ICityService extends IGenericService<City> {
	public City get(String title);

}
