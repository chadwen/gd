package gd.web.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
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
	public List<DataTable> generateDataTable(String startDate, String endDate, int staId, String direction) {
		// TODO Auto-generated method stub
		ArrayList<DataTable> dataTables = new ArrayList<DataTable>();
		if(Enum.BOTH.toString().equals(direction)){
			List<InStreamEntity> inStreamList = inStreamService.getEntityByDate(startDate,endDate,staId);
			for(InStreamEntity item : inStreamList){
				DataTable dt = new DataTable();
				dt.setDateTime(item.getCurrDate());
				dt.setDirection("ru");
				dt.setStaId(item.getStaId());
				dt.setTotal(item.getTotal());
				dt.setDatas(item.getDatas());
				dataTables.add(dt);
			}
			List<OutStreamEntity> outStreamList = outStreamService.getEntityByDate(startDate,endDate,staId);
			for(OutStreamEntity item : outStreamList){
				DataTable dt = new DataTable();
				dt.setDateTime(item.getCurrDate());
				dt.setDirection("chu");
				dt.setStaId(item.getStaId());
				dt.setTotal(item.getTotal());
				dt.setDatas(item.getDatas());	
				dataTables.add(dt);			
			}

		}else if(Enum.IN.toString().equals(direction)){
			List<InStreamEntity> inStreamList = inStreamService.getEntityByDate(startDate,endDate,staId);
			for(InStreamEntity item : inStreamList){
				DataTable dt = new DataTable();
				dt.setDateTime(item.getCurrDate());
				dt.setDirection("ru");
				dt.setStaId(item.getStaId());
				dt.setTotal(item.getTotal());
				dt.setDatas(item.getDatas());
				dataTables.add(dt);
			}
			
		}else if(Enum.OUT.toString().equals(direction)){
			List<OutStreamEntity> outStreamList = outStreamService.getEntityByDate(startDate,endDate,staId);
			for(OutStreamEntity item : outStreamList){
				DataTable dt = new DataTable();
				dt.setDateTime(item.getCurrDate());
				dt.setDirection("chu");
				dt.setStaId(item.getStaId());
				dt.setTotal(item.getTotal());
				dt.setDatas(item.getDatas());	
				dataTables.add(dt);			
			}
			
		}else{
			return null;
		}
		
		
		return dataTables;
	}
	
}
