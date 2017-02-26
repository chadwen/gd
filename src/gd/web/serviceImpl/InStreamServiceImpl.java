package gd.web.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.InStreamDAO;
import gd.web.entity.InStreamEntity;
import gd.web.service.InStreamService;

@Service
public class InStreamServiceImpl implements InStreamService{

	@Autowired
	private InStreamDAO inStreamDAO;
	@Override
	public void addInStream(InStreamEntity inStreamEntity) {
		// TODO Auto-generated method stub
		inStreamDAO.addInStream(inStreamEntity);
	}

}
