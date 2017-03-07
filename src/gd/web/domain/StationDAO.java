package gd.web.domain;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.StationEntity;

public interface StationDAO {
	@Transactional
	void addStation(StationEntity stationEntity);
	@Transactional
	StationEntity getEntityByAlias(String staAlias);
	@Transactional
	List<StationEntity> getAllStationEntity();
}
