package gd.web.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.InStreamDAO;
import gd.web.entity.ChartDataEntity;
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
	public void inactivate(int staId) {
		// TODO Auto-generated method stub
		inStreamDAO.inactivate(staId);
	}
	@Override
	public void updateInStream(InStreamEntity inStreamEntity) {
		// TODO Auto-generated method stub
		inStreamDAO.updateInStream(inStreamEntity);
	}
	@Override
	public void updateInStreamDatas(ChartDataEntity chartDataEntity) {
		// TODO Auto-generated method stub

		String chartDatas = chartDataEntity.getDatas();
		InStreamEntity inStreamEntity = getActiveRecord(chartDataEntity.getStaId());
		if(inStreamEntity == null || Util.getFormatDate().compareTo(inStreamEntity.getCurrDate())>0){
			createRecord(chartDataEntity.getStaId());
			return;
		}
		String[] datas = inStreamEntity.getDatas().split(",");
		int total = 0;
		for(int i = 0 ; i < datas.length ; i++){
			if(i==chartDataEntity.getCurrHour()){
				datas[i]=chartDatas.substring(chartDatas.lastIndexOf(",")+1);
			}
			total += Integer.parseInt(datas[i]);
		}
		inStreamEntity.setDatas(Util.arrayToString(datas));
		inStreamEntity.setModifyTime(new Date());
		inStreamEntity.setTotal(total);
		updateInStream(inStreamEntity);
	}
	@Override
	public InStreamEntity getActiveRecord(int staId) {
		// TODO Auto-generated method stub
		return inStreamDAO.getActiveRecord(staId);
	}
	@Override
	public void createRecord(int staId) {
		// TODO Auto-generated method stub
		//inactivate current active data
		inactivate(staId);
		// and then new a active data.
		InStreamEntity inStreamEntity = new InStreamEntity();
		inStreamEntity.setStaId(staId);
		inStreamEntity.setCurrDate(Util.getFormatDate());
		addInStream(inStreamEntity);
	}

}
