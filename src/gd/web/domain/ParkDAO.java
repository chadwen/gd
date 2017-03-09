package gd.web.domain;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.ParkEntity;

public interface ParkDAO {
	@Transactional
	void addPark(ParkEntity parkEntity);

	@Transactional
	List<ParkEntity> getAllParkEntity();

	@Transactional
	void updatePark(ParkEntity parkEntity);

	@Transactional
	void deletePark(int id);

	@Transactional
	ParkEntity getParkById(int id);
}
