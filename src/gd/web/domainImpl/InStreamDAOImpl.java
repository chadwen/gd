package gd.web.domainImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.domain.InStreamDAO;
import gd.web.entity.InStreamEntity;

@Repository
public class InStreamDAOImpl implements InStreamDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addInStream(InStreamEntity inStreamEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(inStreamEntity);
	}

	@Override
	public void inactivate(int staId) {
		// TODO Auto-generated method stub
		
		String hql = "from InStreamEntity where active = ? and staId = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
		query.setInteger(1, staId);
		query.setInteger(2, 1);

		List<InStreamEntity> inStreamEntityList = query.list();
		if(inStreamEntityList.size()>0){
			InStreamEntity inStreamEntity = inStreamEntityList.get(0);
			inStreamEntity.setActive(0);
			updateInStream(inStreamEntity);
		}
	}

	@Override
	public void updateInStream(InStreamEntity inStreamEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(inStreamEntity);
	}

	@Override
	public InStreamEntity getActiveRecord(int staId) {
		// TODO Auto-generated method stub
		String hql = "from InStreamEntity where active = ? and staId = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
		query.setInteger(1, staId);
		query.setInteger(2, 1);

		List<InStreamEntity> inStreamEntityList = query.list();
		if(inStreamEntityList.size()>0){
			return inStreamEntityList.get(0);
		}
		return null;
	}

	@Override
	public List<InStreamEntity> getEntityByDate(String startDate, String endDate, int staId) {
		// TODO Auto-generated method stub
		String hql = "from InStreamEntity where staId = ? and isValid = ? and currDate <= ? and currDate >= ? ";
		if(staId==0){
			hql="from InStreamEntity where isValid = ? and currDate <= ? and currDate >= ? ";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(staId ==0 ){
			query.setInteger(0, 1);	
			query.setString(1, endDate);
			query.setString(2, startDate);			
		}else{
			query.setInteger(0, staId);
			query.setInteger(1, 1);
			query.setString(2, endDate);
			query.setString(3, startDate);
		}
		return query.list();
	}

	@Override
	public InStreamEntity getStreamByDateAndStaId(String date, int staId) {
		// TODO Auto-generated method stub

		String hql = "from InStreamEntity where currDate = ? and staId = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, date);
		query.setInteger(1, staId);
		query.setInteger(2, 1);

		List<InStreamEntity> inStreamEntityList = query.list();
		if(inStreamEntityList.size()>0){
			return inStreamEntityList.get(0);
		}
		return null;
	}
}
