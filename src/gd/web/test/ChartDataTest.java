package gd.web.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gd.web.entity.ChartDataEntity;
import gd.web.service.ChartDataService;

@Component
public class ChartDataTest {

	@Autowired
	private ChartDataService chartDataService;
	
	
	@Test
	public void addChartData(){
		ChartDataEntity cde = new ChartDataEntity();

		cde.setStaId(0);
		chartDataService.addChartData(cde);
	}
}
