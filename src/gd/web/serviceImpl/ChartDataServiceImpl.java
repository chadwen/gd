package gd.web.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.ChartDataDAO;
import gd.web.entity.ChartDataEntity;
import gd.web.entity.InStreamEntity;
import gd.web.entity.OutStreamEntity;
import gd.web.service.ChartDataService;
import gd.web.service.InStreamService;
import gd.web.service.OutStreamService;
import gd.web.util.Util;
import gd.web.util.Enum;

@Service
public class ChartDataServiceImpl implements ChartDataService{
	@Autowired
	private ChartDataDAO chartDataDAO;
	
	@Autowired
	private InStreamService inStreamService;
	
	@Autowired
	private OutStreamService outStreamService;
	
	@Override
	public void addChartData(ChartDataEntity chartData) {
		// TODO Auto-generated method stub
		chartDataDAO.addChartData(chartData);
	}
	@Override
	public void updateChartData(ChartDataEntity chartData) {
		// TODO Auto-generated method stub
		chartDataDAO.updateChartData(chartData);
	}
	@Override
	public ChartDataEntity getEntityById(int id) {
		// TODO Auto-generated method stub
		return chartDataDAO.getEntityById(id);
	}
	@Override
	public void processStreamTable(String direction,int staId,String datas) {
		// TODO Auto-generated method stub
		if(Enum.IN.toString().equals(direction)){
			//update the active data.
			inStreamService.updateInStreamDatas(staId,datas);
		}
		if(Enum.OUT.toString().equals(direction)){
			//update the active data.
			outStreamService.updateOutStreamDatas(staId,datas);

		}
		
	}
	@Override
	public ChartDataEntity getEntityByStaId(int staId, String direction) {
		// TODO Auto-generated method stub
		return chartDataDAO.getEntityByStaId(staId,direction);
	}
	@Override
	public void resetChart(int missHour,int staId) {
		// TODO Auto-generated method stub
		ChartDataEntity chartDataEntity = getEntityByStaId(staId,Enum.IN.toString());
		ArrayList<String> dataList = (ArrayList<String>) Arrays.asList(chartDataEntity.getDatas().split(","));
		for (int i = 0; i <missHour ; i++) {
			dataList.remove(i);
			dataList.add("0");
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i<dataList.size()-1;i++){
			sb.append(dataList.get(i)+",");
		}
		sb.append(dataList.get(dataList.size()-1));
	}

}
