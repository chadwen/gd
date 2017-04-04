package gd.web.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.StationDAO;
import gd.web.entity.StationEntity;
import gd.web.entity.viewModel.PointCommon;
import gd.web.service.StationService;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationDAO stationDAO;

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
	
}
