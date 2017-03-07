package gd.web.service;

import java.util.List;

import gd.web.entity.StationEntity;

public interface StationService {

	void addStation(StationEntity stationEntity);

	StationEntity getEntityByAlias(String staAlias);
	
	List<StationEntity> getAllStationEntity();
}
