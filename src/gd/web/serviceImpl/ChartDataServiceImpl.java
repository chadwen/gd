package gd.web.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.ChartDataDAO;
import gd.web.entity.ChartDataEntity;
import gd.web.entity.InStreamEntity;
import gd.web.entity.OutStreamEntity;
import gd.web.entity.viewModel.ChartData;
import gd.web.service.ChartDataService;
import gd.web.service.InStreamService;
import gd.web.service.OutStreamService;
import gd.web.service.StationService;
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
	
	@Autowired
	private StationService stationService;
	
	@Override
	public void processStreamTable(ChartDataEntity chartDataEntity) {
		// TODO Auto-generated method stub
		if(Enum.IN.toString().equals(chartDataEntity.getDirection())){
			//update the active data.
			inStreamService.updateInStreamDatas(chartDataEntity);
		}
		if(Enum.OUT.toString().equals(chartDataEntity.getDirection())){
			//update the active data.
			outStreamService.updateOutStreamDatas(chartDataEntity);

		}
		
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
	
	@Override
	public ChartData getReturnData(int clientHour, int staId) {
		ArrayList<Integer> inDataList = new ArrayList<Integer>();
		ArrayList<Integer> outDataList = new ArrayList<Integer>();
		ArrayList<Integer> hourList = new ArrayList<Integer>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		ChartDataEntity inRecord = getEntityByStaId(staId, Enum.IN.toString());
		ChartDataEntity outRecord = getEntityByStaId(staId, Enum.OUT.toString());
		if(Util.getIntervalDay(inRecord.getModifyTime())>1||Util.getIntervalDay(outRecord.getModifyTime())>1){
			for(int i = 0 ; i < 24 ; i ++){
				inDataList.add(0);
				outDataList.add(0);
			}
		}else{
			
			inDataList = Util.stringToList(inRecord.getDatas());
			outDataList = Util.stringToList(outRecord.getDatas());
			
			for(int i = 0 ; i < ((clientHour+24-inRecord.getCurrHour())%24);i++){
				inDataList.remove(0);
				outDataList.remove(0);
				inDataList.add(0);
				outDataList.add(0);
			}
		}
		

		ids.add(outRecord.getId());
		ids.add(inRecord.getId());
		for(int i = 23 ; i>=0 ; i--){
			hourList.add((clientHour+24-i)%24);
		}
		
		ChartData chartData = new ChartData();
		chartData.setOutDataList(outDataList);
		chartData.setInDataList(inDataList);
		chartData.setHourList(hourList);
		chartData.setIds(ids);
		chartData.setStaId(staId);
		chartData.setBrief(stationService.getStationById(staId).getBrief());
		return chartData;
	}
	
	@Override
	public ChartData getWholeWholeData(int clientHour){
		
		List<ChartDataEntity> inList = getChartDataByDirection(Enum.IN.toString());
		List<ChartDataEntity> outList = getChartDataByDirection(Enum.OUT.toString());
		ArrayList<Integer> inDataList = new ArrayList<Integer>();
		ArrayList<Integer> outDataList = new ArrayList<Integer>();
		for(int i = 0 ; i < 24 ; i++){
			inDataList.add(0);
			outDataList.add(0);
		}
		for(ChartDataEntity item : inList){
			Util.countList(inDataList,item.getDatas());
		}
		for(ChartDataEntity item : outList){
			Util.countList(outDataList,item.getDatas());
		}
		ArrayList<Integer> hourList = new ArrayList<Integer>();
		for(int i = 23 ; i>=0 ; i--){
			hourList.add((clientHour+24-i)%24);
		}
		
		
		ChartData chartData = new ChartData();
		chartData.setOutDataList(outDataList);
		chartData.setInDataList(inDataList);
		chartData.setHourList(hourList);
		return chartData;
	}
	
	@Override
	public List<ChartDataEntity> getChartDataByDirection(String direction) {
		return chartDataDAO.getChartDataByDirection(direction);
	}

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
	public ChartDataEntity getEntityByStaId(int staId, String direction) {
		// TODO Auto-generated method stub
		return chartDataDAO.getEntityByStaId(staId,direction);
	}
	
	

}
