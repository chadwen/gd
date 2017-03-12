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
	public void inactivate() {
		// TODO Auto-generated method stub
		
		String hql = "from InStreamEntity where active = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 1);

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
	public InStreamEntity getActiveRecord() {
		// TODO Auto-generated method stub
		String hql = "from InStreamEntity where active = ? and direction = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 0);
		query.setString(1, "IN");

		List<InStreamEntity> inStreamEntityList = query.list();
		if(inStreamEntityList.size()>0){
			return inStreamEntityList.get(0);
		}
		return null;
	}
}
