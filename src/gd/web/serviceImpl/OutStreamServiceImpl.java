package gd.web.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.OutStreamDAO;
import gd.web.entity.ChartDataEntity;
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
	public void inactivate(int staId) {
		// TODO Auto-generated method stub
		outStreamDAO.inactivate(staId);
	}

	@Override
	public void updateOutStream(OutStreamEntity outStreamEntity) {
		// TODO Auto-generated method stub
		outStreamDAO.updateOutStream(outStreamEntity);
	}

	@Override
	public void updateOutStreamDatas(ChartDataEntity chartDataEntity) {
		// TODO Auto-generated method stub
		String chartDatas = chartDataEntity.getDatas();
		OutStreamEntity outStreamEntity = getActiveRecord(chartDataEntity.getStaId());
		if(outStreamEntity==null || Util.getFormatDate().compareTo(outStreamEntity.getCurrDate())>0){
			//new a record
			createRecord(chartDataEntity.getStaId());
			return;
		}
		String[] datas = outStreamEntity.getDatas().split(",");
		int total = 0;
		for(int i = 0 ; i < datas.length ; i++){
			if(i==chartDataEntity.getCurrHour()){
				datas[i]=chartDatas.substring(chartDatas.lastIndexOf(",")+1);
			}
			total += Integer.parseInt(datas[i]);
		}
		outStreamEntity.setDatas(Util.arrayToString(datas));
		outStreamEntity.setModifyTime(new Date());
		outStreamEntity.setTotal(total);
		updateOutStream(outStreamEntity);
	}

	@Override
	public OutStreamEntity getActiveRecord(int staId) {
		// TODO Auto-generated method stub
		return outStreamDAO.getActiveRecord(staId);
	}

	@Override
	public void createRecord(int staId) {
		// TODO Auto-generated method stub

		//inactivate current active data
		inactivate(staId);
		// and then new a active data.
		OutStreamEntity outStreamEntity = new OutStreamEntity();
		outStreamEntity.setStaId(staId);
		outStreamEntity.setCurrDate(Util.getFormatDate());
		addOutStream(outStreamEntity);
	}

	@Override
	public List<OutStreamEntity> getEntityByDate(String startDate, String endDate, int staId) {
		// TODO Auto-generated method stub
		
		return outStreamDAO.getEntityByDate(startDate,endDate,staId);
	}

	@Override
	public OutStreamEntity getStreamByDateAndStaId(String date, int staId) {
		// TODO Auto-generated method stub
		return outStreamDAO.getStreamByDateAndStaId(date, staId);
	}
}
