package ru.komiparma.manifest.dao;

import java.util.List;

public interface IGenericDAO<T> {
	public void saveEntity(T entity);
	
	public void removeEntity(T entity);
	
	public T getEntity(Integer id);
	
	public List<T> getAllEntity();
	
}
