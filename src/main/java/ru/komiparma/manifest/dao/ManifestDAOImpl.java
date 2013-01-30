package ru.komiparma.manifest.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.domain.City;
import ru.komiparma.manifest.domain.Manifest;
@Repository
public class ManifestDAOImpl extends GenericDAOImpl<Manifest> implements IManifestDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ICityDAO cityDAO;
	
	public ManifestDAOImpl(){
		super(Manifest.class);
	}
	
	/**
	 * ѕринимает в качестве аргумента название города и дату.
	 * ¬озвращает список манифестов дл€ города, добавленых 
	 * после указаной даты
	 */
	public List<Manifest> getManifestPeriodDate(String cityTitle, Date date){
		if((date!=null)&&(cityTitle!=null)){
			City city = this.cityDAO.getCityByTitle(cityTitle);
			java.sql.Date d = new java.sql.Date(date.getTime());
			@SuppressWarnings("unchecked")
			List<Manifest> manifestList=this.sessionFactory.getCurrentSession().createQuery(
					"from Manifest where city= '"+city.getId()+"' and date_recive >= '"+d+"'").list();
			return manifestList;
		} else {
			return null;
		}
	}
}
