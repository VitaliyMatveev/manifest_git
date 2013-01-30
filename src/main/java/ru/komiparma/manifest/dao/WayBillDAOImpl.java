package ru.komiparma.manifest.dao;

import java.sql.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.komiparma.manifest.domain.WayBill;

@Repository
public class WayBillDAOImpl extends GenericDAOImpl<WayBill> implements IWayBillDAO{
	private static final Logger log = Logger.getLogger(WayBill.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public WayBillDAOImpl(){
		super(WayBill.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<WayBill> getWBNumbersForDiaposon(Date stDate, Date endDate) {
		try{
			if((stDate!=null)&&(endDate!=null)){
				Criteria cr = this.getSessionFactory().getCurrentSession().createCriteria(WayBill.class);
				cr.add(Restrictions.between("dateCreate", stDate, endDate));
				return cr.list();
			} else {
				throw new IllegalArgumentException("Required argument stDate or endDate is null"); 
			}
		} catch (IllegalArgumentException e){
			log.error(e.getLocalizedMessage(),e);
		} 
		return null;
	}
	
	public List<WayBill> getWayBillsByCity(String city){
	//	List<WayBill> wbList = sessionFactory.getCurrentSession().	
		return null;
	}
	
	public WayBill get(String title){
		title=title.trim();
		WayBill wb= (WayBill) this.sessionFactory.getCurrentSession().createQuery("from WayBill where title like '%"+title+"%'").uniqueResult();
		if(wb!=null)
			return wb;
		else
			return null;
	}
}
