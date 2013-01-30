package ru.komiparma.manifest.service;

import java.util.List;

public interface IGenericService<T> {
	public void save(T object);
	
	public void remove(T object);
	
	public void remove(Integer id);
	
	public List<T> getAll();
	
	public T get(Integer id);
	
	public void removeAll();
	
}
