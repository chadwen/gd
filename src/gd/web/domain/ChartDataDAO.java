package gd.web.domain;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.ChartDataEntity;
public interface ChartDataDAO {
	
	@Transactional
	void addChartData(ChartDataEntity chartData);
	
	@Transactional
	void updateChartData(ChartDataEntity chartData);
	
	@Transactional
	ChartDataEntity getEntityById(int id);

	@Transactional
	ChartDataEntity getEntityByStaId(int staId, String direction);
}
