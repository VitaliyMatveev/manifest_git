package ru.komiparma.manifest.dao;

import java.util.Date;
import java.util.List;

import ru.komiparma.manifest.domain.Manifest;

public interface IManifestDAO extends IGenericDAO<Manifest> {
	public List<Manifest> getManifestPeriodDate(String city, Date date);
}
