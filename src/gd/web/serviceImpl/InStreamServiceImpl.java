package gd.web.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.InStreamDAO;
import gd.web.entity.InStreamEntity;
import gd.web.service.InStreamService;
import gd.web.util.Util;

@Service
public class InStreamServiceImpl implements InStreamService{

	@Autowired
	private InStreamDAO inStreamDAO;
	@Override
	public void addInStream(InStreamEntity inStreamEntity) {
		// TODO Auto-generated method stub
		inStreamDAO.addInStream(inStreamEntity);
	}
	@Override
	public void inactivate() {
		// TODO Auto-generated method stub
		inStreamDAO.inactivate();
	}
	@Override
	public void updateInStream(InStreamEntity inStreamEntity) {
		// TODO Auto-generated method stub
		inStreamDAO.updateInStream(inStreamEntity);
	}
	@Override
	public void updateInStreamDatas(int staId,String datas) {
		// TODO Auto-generated method stub
		InStreamEntity inStreamEntity = getActiveRecord();
		if(inStreamEntity == null || Util.getFormatDate().compareTo(inStreamEntity.getCurrDate())>0){
			createRecord(staId);
			return;
		}
		inStreamEntity.setDatas(datas);
		inStreamEntity.setModifyTime(new Date());
		updateInStream(inStreamEntity);
	}
	@Override
	public InStreamEntity getActiveRecord() {
		// TODO Auto-generated method stub
		return inStreamDAO.getActiveRecord();
	}
	@Override
	public void createRecord(int staId) {
		// TODO Auto-generated method stub
		//inactivate current active data
		inactivate();
		// and then new a active data.
		InStreamEntity inStreamEntity = new InStreamEntity();
		inStreamEntity.setStaId(staId);
		inStreamEntity.setCurrDate(Util.getFormatDate());
		addInStream(inStreamEntity);
	}

}
