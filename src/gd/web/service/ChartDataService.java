package gd.web.service;

import gd.web.entity.ChartDataEntity;
public interface ChartDataService {

	void addChartData(ChartDataEntity chartData);
	
	void updateChartData(ChartDataEntity chartData);
	
	ChartDataEntity getEntityById(int id);

	void processStreamTable(String direction,int staId,String datas);

	ChartDataEntity getEntityByStaId(int staId, String direction);

	void resetChart(int missHour,int staId);
}
