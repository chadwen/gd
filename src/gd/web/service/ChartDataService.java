package gd.web.service;

import java.util.List;

import gd.web.entity.ChartDataEntity;
import gd.web.entity.viewModel.ChartData;

public interface ChartDataService {

	void addChartData(ChartDataEntity chartDataEntity);
	
	void updateChartData(ChartDataEntity chartDataEntity);
	
	ChartDataEntity getEntityById(int id);

	void processStreamTable(ChartDataEntity chartDataEntity);

	ChartDataEntity getEntityByStaId(int staId, String direction);

	void resetChart(int missHour,int staId);

	ChartData getReturnData(int clientHour, int staId);

	List<ChartDataEntity> getChartDataByDirection(String direction);

	ChartData getWholeWholeData(int clientHour);
}
