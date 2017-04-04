package gd.web.service;

import gd.web.entity.ChartDataEntity;
import gd.web.entity.InStreamEntity;

public interface InStreamService {

	void addInStream(InStreamEntity inStreamEntity);
	
	void inactivate(int staId);
	
	void updateInStream(InStreamEntity inStreamEntity);

	void updateInStreamDatas(ChartDataEntity chartDataEntity);
	
	InStreamEntity getActiveRecord(int staId);

	void createRecord(int staId);
}
