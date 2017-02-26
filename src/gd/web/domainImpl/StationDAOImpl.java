package gd.web.domainImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.domain.StationDAO;
import gd.web.entity.StationEntity;

@Repository
public class StationDAOImpl implements StationDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addStation(StationEntity stationEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(stationEntity);
	}
}
