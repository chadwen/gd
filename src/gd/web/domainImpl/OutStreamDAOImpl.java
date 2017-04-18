package gd.web.domainImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.domain.OutStreamDAO;
import gd.web.entity.OutStreamEntity;

@Repository
public class OutStreamDAOImpl implements OutStreamDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addOutStream(OutStreamEntity outStreamEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(outStreamEntity);
		
	}

	@Override
	public void inactivate(int staId) {
		// TODO Auto-generated method stub

		String hql = "from OutStreamEntity where active = ? and staId = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
		query.setInteger(1, staId);
		query.setInteger(2, 1);
		List<OutStreamEntity> outStreamEntityList = query.list();
		if(outStreamEntityList.size()>0){
			OutStreamEntity outStreamEntity = outStreamEntityList.get(0);
			outStreamEntity.setActive(0);
			updateOutStream(outStreamEntity);
			
		}
	}

	@Override
	public void updateOutStream(OutStreamEntity outStreamEntity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(outStreamEntity);
	}

	@Override
	public OutStreamEntity getActiveRecord(int staId) {
		// TODO Auto-generated method stub


		String hql = "from OutStreamEntity where active = ? and staId = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
		query.setInteger(1, staId);
		query.setInteger(2, 1);
		List<OutStreamEntity> outStreamEntityList = query.list();
		if(outStreamEntityList.size()>0){
			return outStreamEntityList.get(0);			
		}
		return null;
	}

	@Override
	public List<OutStreamEntity> getEntityByDate(String startDate, String endDate, int staId) {
		// TODO Auto-generated method stub
		
		String hql = "from OutStreamEntity where staId = ? and isValid = ? and currDate <= ? and currDate >= ? ";
		if(staId==0){
			hql="from OutStreamEntity where isValid = ? and currDate <= ? and currDate >= ? ";
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
}
