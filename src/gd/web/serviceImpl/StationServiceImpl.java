package gd.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.StationDAO;
import gd.web.entity.StationEntity;
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
	
}
