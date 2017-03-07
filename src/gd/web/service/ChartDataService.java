package gd.web.service;

import gd.web.entity.ChartDataEntity;
public interface ChartDataService {

	void addChartData(ChartDataEntity chartData);
	
	void updateChartData(ChartDataEntity chartData);
	
	ChartDataEntity getEntityById(int id);
}
