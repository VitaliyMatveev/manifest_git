package ru.komiparma.manifest.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDAOImpl<T> implements IGenericDAO<T>{
	
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> entityClass;
	
	public Class<T> getEntityClass(){
		return this.entityClass;
	}
	public GenericDAOImpl(){
		
	}
	public GenericDAOImpl(Class<T> entityClass){
		this.entityClass=entityClass;
	}
	

	public void saveEntity(Object entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}


	public void removeEntity(Object entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public T getEntity(Integer id) {
		return (T)getSessionFactory().getCurrentSession().get(entityClass, id);
	}

	
	@SuppressWarnings("unchecked")
	
	public List<T> getAllEntity() {
		return  getSessionFactory().getCurrentSession().createQuery("from "+this.entityClass.getName()).list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
