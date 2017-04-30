package gd.web.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.StationDAO;
import gd.web.entity.InStreamEntity;
import gd.web.entity.OutStreamEntity;
import gd.web.entity.StationEntity;
import gd.web.entity.viewModel.DataTable;
import gd.web.entity.viewModel.PointCommon;
import gd.web.service.InStreamService;
import gd.web.service.OutStreamService;
import gd.web.service.StationService;
import gd.web.util.Enum;
import gd.web.util.Util;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationDAO stationDAO;
	
	@Autowired
	private InStreamService inStreamService;
	
	@Autowired
	private OutStreamService outStreamService;

	@Override
	public void addStation(StationEntity stationEntity) {
		// TODO Auto-generated method stub
		stationDAO.addStation(stationEntity);
	}

	@Override
	public StationEntity getEntityByAlias(String staAlias) {
		// TODO Auto-generated method stub
		return stationDAO.getEntityByAlias(staAlias);
	}

	@Override
	public List<StationEntity> getAllStationEntity() {
		// TODO Auto-generated method stub
		return stationDAO.getAllStationEntity();
	}

	@Override
	public void updateStation(StationEntity stationEntity) {
		// TODO Auto-generated method stub
		stationDAO.updateStation(stationEntity);
	}

	@Override
	public void deleteStation(int id) {
		// TODO Auto-generated method stub
		stationDAO.deleteStation(id);
	}

	@Override
	public StationEntity getStationById(int id) {
		// TODO Auto-generated method stub
		return stationDAO.getStationById(id);
	}

	@Override
	public StationEntity convertToStation(PointCommon pointCommon) {
		// TODO Auto-generated method stub
		StationEntity stationEntity = getStationById(pointCommon.getId());
		
		stationEntity.setAlias(pointCommon.getAlias());
		stationEntity.setFullName(pointCommon.getFullName());
		stationEntity.setAddr(pointCommon.getAddr());
		stationEntity.setBrief(pointCommon.getBrief());		
		
		stationEntity.setModifyTime(new Date());
		return stationEntity;
	}

	@Override
	public List<DataTable> generateDataTable(String startDate, String endDate, List<Integer> staIds, String direction) {
		// TODO Auto-generated method stub
		ArrayList<DataTable> dataTables = new ArrayList<DataTable>();
		
		if(Enum.BOTH.toString().equals(direction)){
			for(int staId : staIds){
				addInToDataTables(startDate,endDate,staId,dataTables);
				addOutToDataTables(startDate,endDate,staId,dataTables);
			}

		}else if(Enum.IN.toString().equals(direction)){
			for(int staId : staIds){
				addInToDataTables(startDate,endDate,staId,dataTables);
			}
			
		}else if(Enum.OUT.toString().equals(direction)){
			for(int staId : staIds){
				addOutToDataTables(startDate,endDate,staId,dataTables);
			}
			
		}else{
			return null;
		}
		
		
		return dataTables;
	}
	private String getStaBrief(int staId){
		StationEntity se = getStationById(staId);
		if(se!=null){
			return se.getBrief();
		}
		return "";
	}
	private void addOutToDataTables(String startDate, String endDate, int staId,ArrayList<DataTable> dataTables){

		List<OutStreamEntity> outStreamList = outStreamService.getEntityByDate(startDate,endDate,staId);
		for(OutStreamEntity item : outStreamList){
			DataTable dt = new DataTable();
			dt.setDateTime(item.getCurrDate());
			dt.setDirection("出");
			dt.setStaId(item.getStaId());
			dt.setTotal(item.getTotal());
			dt.setDatas(item.getDatas());	
			if(dt.getTotal() != -1){
				dt.setAverage(Util.getFormatDouble(dt.getTotal()/24.0));
			}else{
				dt.setAverage(0);
			}
			dt.setStaBrief(getStaBrief(dt.getStaId()));
			dataTables.add(dt);			
		}
	}
	private void addInToDataTables(String startDate, String endDate, int staId,ArrayList<DataTable> dataTables){
		List<InStreamEntity> inStreamList = inStreamService.getEntityByDate(startDate,endDate,staId);
		for(InStreamEntity item : inStreamList){
			DataTable dt = new DataTable();
			dt.setDateTime(item.getCurrDate());
			dt.setDirection("入");
			dt.setStaId(item.getStaId());
			dt.setTotal(item.getTotal());
			dt.setDatas(item.getDatas());
			if(dt.getTotal() != -1){
				dt.setAverage(Util.getFormatDouble(dt.getTotal()/24.0));
			}else{
				dt.setAverage(0);
			}
			dt.setStaBrief(getStaBrief(dt.getStaId()));
			dataTables.add(dt);
		}
	}
	@Override
	public List<DataTable> generateDataTableCal(String startDate, String endDate, List<Integer> staIds, String direction) throws ParseException{
		
		List<String> dateList = Util.getStringDateList(startDate,endDate);
		if(dateList == null || dateList.size() ==0){
			return null;
		}
		List<DataTable> dataTables = new ArrayList<DataTable>();

		if(Enum.BOTH.toString().equals(direction)){
			
			generateDataTableCalIN(dateList,staIds,dataTables);
			generateDataTableCalOUT(dateList,staIds,dataTables);
			
			
		}else if(Enum.IN.toString().equals(direction)){
			generateDataTableCalIN(dateList,staIds,dataTables);
			
		}else if(Enum.OUT.toString().equals(direction)){
			generateDataTableCalOUT(dateList,staIds,dataTables);			
		}else{
			return null;
		}
		return dataTables;
	}
	private void generateDataTableCalIN(List<String> dateList, List<Integer> staIds, List<DataTable> dataTables){
		for(String date : dateList){
			DataTable dtIN = new DataTable();
			dtIN.setDateTime(date);
			dtIN.setBriefSet(new HashSet<String>());
			dtIN.setDirection("入");
			List<Integer> datasList = new ArrayList<Integer>();
			for(int i = 0; i < 24; i++){
				datasList.add(0);
			}
			for(int staId : staIds){
				getDataTableEntityIN(inStreamService.getStreamByDateAndStaId(date,staId),dtIN,datasList);
			}
			if(dtIN.getBriefSet().size()==0){
				continue;
			}

			if(dtIN.getTotal() != -1){
				dtIN.setAverage(Util.getFormatDouble(dtIN.getTotal()/24.0));
			}else{
				dtIN.setAverage(0);
			}
			dtIN.setDatas(Util.listToString(datasList));
			dtIN.setStaBrief(Util.listToString(new ArrayList(dtIN.getBriefSet())));
			dataTables.add(dtIN);
		}
	}

	private void generateDataTableCalOUT(List<String> dateList, List<Integer> staIds, List<DataTable> dataTables){
		for(String date : dateList){
			DataTable dtOUT = new DataTable();
			dtOUT.setDateTime(date);
			dtOUT.setBriefSet(new HashSet<String>());
			dtOUT.setDirection("出");
			List<Integer> datasList = new ArrayList<Integer>();
			for(int i = 0; i < 24; i++){
				datasList.add(0);
			}
			for(int staId : staIds){
				getDataTableEntityOUT(outStreamService.getStreamByDateAndStaId(date,staId),dtOUT,datasList);
			}
			if(dtOUT.getBriefSet().size()==0){
				continue;
			}

			if(dtOUT.getTotal() != -1){
				dtOUT.setAverage(Util.getFormatDouble(dtOUT.getTotal()/24.0));
			}else{
				dtOUT.setAverage(0);
			}
			dtOUT.setDatas(Util.listToString(datasList));
			dtOUT.setStaBrief(Util.listToString(new ArrayList(dtOUT.getBriefSet())));
			dataTables.add(dtOUT);
		}
	}
	private void getDataTableEntityIN(InStreamEntity ise,DataTable dt,List<Integer> datasList){
		if(ise == null){
			return;
		}
		HashSet<String> set = dt.getBriefSet();
		set.add(getStaBrief(ise.getStaId()));
		dt.setBriefSet(set);
		dt.setTotal(dt.getTotal()+ise.getTotal());
		List<Integer> list = Util.stringToList(ise.getDatas());
		for(int i = 0; i < datasList.size(); i++){
			datasList.set(i, datasList.get(i)+list.get(i));
		}
	}
	private void getDataTableEntityOUT(OutStreamEntity ose,DataTable dt,List<Integer> datasList){
		if(ose == null){
			return;
		}
		HashSet<String> set = dt.getBriefSet();
		set.add(getStaBrief(ose.getStaId()));
		dt.setBriefSet(set);
		dt.setTotal(dt.getTotal()+ose.getTotal());
		List<Integer> list = Util.stringToList(ose.getDatas());
		for(int i = 0; i < datasList.size(); i++){
			datasList.set(i, datasList.get(i)+list.get(i));
		}
	}
}
