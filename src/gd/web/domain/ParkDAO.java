package gd.web.domain;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.ParkEntity;

public interface ParkDAO {
	@Transactional
	void addPark(ParkEntity parkEntity);
}
