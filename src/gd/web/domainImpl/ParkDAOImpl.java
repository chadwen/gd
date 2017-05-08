package gd.web.domainImpl;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public List<ParkEntity> getAllParkEntity() {
		// TODO Auto-generated method stub
		String hql = "from ParkEntity where isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
		List<ParkEntity> entities = query.list();
		return entities;
	}

	@Override
	public void updatePark(ParkEntity parkEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(parkEntity);
	}

	@Override
	public void deletePark(int id) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(ParkEntity.class, id));
		ParkEntity pe = getParkById(id);
		if(pe == null){
			return;
		}
		pe.setIsValid(0);
		updatePark(pe);
	}

	@Override
	public ParkEntity getParkById(int id) {
		// TODO Auto-generated method stub
		return (ParkEntity)sessionFactory.getCurrentSession().get(ParkEntity.class, id);
	}
}
