package gd.web.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.OutStreamDAO;
import gd.web.entity.OutStreamEntity;
import gd.web.service.OutStreamService;
import gd.web.util.Util;

@Service
public class OutStreamServiceImpl implements OutStreamService{

	@Autowired
	private OutStreamDAO outStreamDAO;

	@Override
	public void addOutStream(OutStreamEntity outStreamEntity) {
		// TODO Auto-generated method stub
		outStreamDAO.addOutStream(outStreamEntity);
		
	}

	@Override
	public void inactivate() {
		// TODO Auto-generated method stub
		outStreamDAO.inactivate();
	}

	@Override
	public void updateOutStream(OutStreamEntity outStreamEntity) {
		// TODO Auto-generated method stub
		outStreamDAO.updateOutStream(outStreamEntity);
	}

	@Override
	public void updateOutStreamDatas(int staId,String datas) {
		// TODO Auto-generated method stub
		OutStreamEntity outStreamEntity = getActiveRecord();
		if(outStreamEntity==null || Util.getFormatDate().compareTo(outStreamEntity.getCurrDate())>0){
			//new a record
			createRecord(staId);
			return;
		}
		outStreamEntity.setDatas(datas);
		outStreamEntity.setModifyTime(new Date());
		updateOutStream(outStreamEntity);
	}

	@Override
	public OutStreamEntity getActiveRecord() {
		// TODO Auto-generated method stub
		return outStreamDAO.getActiveRecord();
	}

	@Override
	public void createRecord(int staId) {
		// TODO Auto-generated method stub

		//inactivate current active data
		inactivate();
		// and then new a active data.
		OutStreamEntity outStreamEntity = new OutStreamEntity();
		outStreamEntity.setStaId(staId);
		outStreamEntity.setCurrDate(Util.getFormatDate());
		addOutStream(outStreamEntity);
	}
}
