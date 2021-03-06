package gd.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.ParkDAO;
import gd.web.entity.ParkEntity;
import gd.web.service.ParkService;

@Service
public class ParkServiceImpl implements ParkService{

	@Autowired
	private ParkDAO parkDAO;
	@Override
	public void addPark(ParkEntity parkEntity) {
		// TODO Auto-generated method stub
		parkDAO.addPark(parkEntity);
	}
	@Override
	public List<ParkEntity> getAllParkEntity() {
		// TODO Auto-generated method stub
		return parkDAO.getAllParkEntity();
	}
	@Override
	public void updatePark(ParkEntity parkEntity) {
		// TODO Auto-generated method stub
		parkDAO.updatePark(parkEntity);
	}
	@Override
	public void deletePark(int id) {
		// TODO Auto-generated method stub
		parkDAO.deletePark(id);
	}
	@Override
	public ParkEntity getParkById(int id) {
		// TODO Auto-generated method stub
		return parkDAO.getParkById(id);
	}

}
