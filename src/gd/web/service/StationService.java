package gd.web.service;

import java.util.List;

import gd.web.entity.StationEntity;
import gd.web.entity.viewModel.PointCommon;

public interface StationService {

	void addStation(StationEntity stationEntity);

	StationEntity getEntityByAlias(String staAlias);
	
	List<StationEntity> getAllStationEntity();

	void updateStation(StationEntity stationEntity);

	void deleteStation(int id);

	StationEntity getStationById(int id);

	StationEntity convertToStation(PointCommon pointCommon);
}
