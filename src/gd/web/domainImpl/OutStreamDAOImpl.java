package gd.web.domainImpl;

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
}
