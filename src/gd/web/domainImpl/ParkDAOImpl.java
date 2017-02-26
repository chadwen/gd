package gd.web.domainImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.domain.ParkDAO;
import gd.web.entity.ParkEntity;

@Repository
public class ParkDAOImpl implements ParkDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPark(ParkEntity parkEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(parkEntity);
		
	}
}
