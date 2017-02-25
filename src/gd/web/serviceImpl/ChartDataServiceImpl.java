package gd.web.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.ChartDataDAO;
import gd.web.entity.ChartDataEntity;
import gd.web.service.ChartDataService;

@Service
public class ChartDataServiceImpl implements ChartDataService{
	@Autowired
	private ChartDataDAO chartDataDAO;
	@Override
	public void addChartData(ChartDataEntity chartData) {
		// TODO Auto-generated method stub
		chartDataDAO.addChartData(chartData);
	}

}
