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

}
