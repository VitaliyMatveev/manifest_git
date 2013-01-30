package ru.komiparma.manifest.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.komiparma.manifest.dao.IGenericDAO;
@Repository
public class GenericServiceImpl<T> implements IGenericService<T> {

	private IGenericDAO<T> entityDAO;
	
	public IGenericDAO<T> getEntityDAO(){
		return this.entityDAO;
	}
	public GenericServiceImpl(){
	}
	
	public GenericServiceImpl(IGenericDAO<T> entityDAO){
		this.entityDAO=entityDAO;
	}
	
	@Transactional
	public void save(T object) {
		getEntityDAO().saveEntity(object);
	}

	@Transactional
	public void remove(T object) {
		getEntityDAO().removeEntity(object);
		
	}

	@Transactional
	public void remove(Integer id) {
		getEntityDAO().removeEntity(getEntityDAO().getEntity(id));
	}

	@Transactional(readOnly=true)
	public List<T> getAll() {
		return getEntityDAO().getAllEntity();
	}

	@Transactional(readOnly=true)
	public T get(Integer id) {
		if(id==null){
			return null;
		}
		return getEntityDAO().getEntity(id);
	}

	@Transactional
	public void removeAll() {
		for(T entity:getEntityDAO().getAllEntity()){
			getEntityDAO().removeEntity(entity);
		}
	}

}
