package gd.web.domainImpl;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public StationEntity getEntityByAlias(String staAlias) {
		// TODO Auto-generated method stub
		String hql = "from StationEntity where staAlias = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, staAlias);
		query.setInteger(1, 1);
		List<StationEntity> entities = query.list();
		if(entities.size()!=0){
			//System.out.println("\n\n\nhere is the userName2: " + ur.get(0).getUserName());
			return entities.get(0);
		}
		return null;
	}

	@Override
	public List<StationEntity> getAllStationEntity() {
		// TODO Auto-generated method stub
		String hql = "from StationEntity where isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
		List<StationEntity> entities = query.list();
		return entities;
	}

	@Override
	public void updateStation(StationEntity stationEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(stationEntity);
	}

	@Override
	public void deleteStation(int id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(StationEntity.class, id));
		
	}

	@Override
	public StationEntity getStationById(int id) {
		// TODO Auto-generated method stub
		return (StationEntity)sessionFactory.getCurrentSession().get(StationEntity.class, id);
	}
}
