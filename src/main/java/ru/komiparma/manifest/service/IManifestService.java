package ru.komiparma.manifest.service;

import java.util.Date;
import java.util.List;

import ru.komiparma.manifest.domain.Manifest;

public interface IManifestService extends IGenericService<Manifest>{
	public List<Manifest> getManifestPeriodDate(String city, Date date);
}
