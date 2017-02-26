package gd.web.domainImpl;

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
}
