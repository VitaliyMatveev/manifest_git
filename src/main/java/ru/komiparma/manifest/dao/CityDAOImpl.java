package ru.komiparma.manifest.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.komiparma.manifest.domain.City;

@Repository
public class CityDAOImpl extends GenericDAOImpl<City> implements ICityDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public CityDAOImpl() {
		super(City.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public City getCityByTitle(String title) {
		City city=(City) this.sessionFactory.getCurrentSession().createQuery("from City where title= '"+title+"'").list().get(0);
		return city;
	}
	
	

}
