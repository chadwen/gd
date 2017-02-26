package gd.web.serviceImpl;

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
	
}
