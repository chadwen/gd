package gd.web.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.OutStreamDAO;
import gd.web.entity.OutStreamEntity;
import gd.web.service.OutStreamService;

@Service
public class OutStreamServiceImpl implements OutStreamService{

	@Autowired
	private OutStreamDAO outStreamDAO;

	@Override
	public void addOutStream(OutStreamEntity outStreamEntity) {
		// TODO Auto-generated method stub
		outStreamDAO.addOutStream(outStreamEntity);
		
	}
}
