package gd.web.domainImpl;

import java.util.List;

import org.hibernate.Query;
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
	@Override
	public ChartDataEntity getEntityByStaId(int staId,String direction) {
		// TODO Auto-generated method stub
		String hql = "from ChartDataEntity where staId = ? and direction = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, staId);
		query.setString(1, direction);
		query.setInteger(2, 1);
		
		List<ChartDataEntity> chartDataEntityList = query.list();
		if(chartDataEntityList.size()>0){
			return chartDataEntityList.get(0);
		}
		
		return null;
	}
	@Override
	public List<ChartDataEntity> getChartDataByDirection(String direction) {
		String hql = "from ChartDataEntity where direction = ? and isValid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, direction);
		query.setInteger(1, 1);
		List<ChartDataEntity> chartDataEntityList = query.list();
		return chartDataEntityList;
	}

}
