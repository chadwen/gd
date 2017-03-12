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
	public void inactivate() {
		// TODO Auto-generated method stub

		String hql = "from OutStreamEntity where active = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);
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
	public OutStreamEntity getActiveRecord() {
		// TODO Auto-generated method stub


		String hql = "from InStreamEntity where active = ? and direction = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 0);
		query.setString(1, "OUT");
		List<OutStreamEntity> outStreamEntityList = query.list();
		if(outStreamEntityList.size()>0){
			return outStreamEntityList.get(0);			
		}
		return null;
	}
}
