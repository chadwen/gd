package gd.web.service;

import java.util.List;

import gd.web.entity.ParkEntity;

public interface ParkService {

	void addPark(ParkEntity parkEntity);

	List<ParkEntity> getAllParkEntity();

	void updatePark(ParkEntity parkEntity);

	void deletePark(int id);

	ParkEntity getParkById(int id);
}
