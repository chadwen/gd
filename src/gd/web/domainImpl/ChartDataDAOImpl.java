package gd.web.domainImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gd.web.domain.ChartDataDAO;
import gd.web.entity.ChartDataEntity;

@Repository
public class ChartDataDAOImpl implements ChartDataDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addChartData(ChartDataEntity chartData) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(chartData);		
	}
	@Override
	public void updateChartData(ChartDataEntity chartData) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(chartData);
		
		
	}
	@Override
	public ChartDataEntity getEntityById(int id) {
		// TODO Auto-generated method stub
		return (ChartDataEntity)sessionFactory.getCurrentSession().get(ChartDataEntity.class, id);
	}

}
