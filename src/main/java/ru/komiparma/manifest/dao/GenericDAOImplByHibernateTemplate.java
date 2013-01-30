package ru.komiparma.manifest.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class GenericDAOImplByHibernateTemplate<T> extends HibernateDaoSupport implements IGenericDAO<T>{

	
	SessionFactory sessionFactory;
	
	private Class<T> entityClass;
	
	@Autowired
	public GenericDAOImplByHibernateTemplate(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public GenericDAOImplByHibernateTemplate(Class<T> entityClass){
		super();
		this.entityClass=entityClass;
	}
	
	@Override
	public void saveEntity(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void removeEntity(T entity) {
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public T getEntity(Integer id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> getAllEntity() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	

}
