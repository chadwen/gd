package gd.web.serviceImpl;

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

}
