package gd.web.service;

import java.util.List;

import gd.web.entity.ChartDataEntity;
import gd.web.entity.InStreamEntity;

public interface InStreamService {

	void addInStream(InStreamEntity inStreamEntity);
	
	void inactivate(int staId);
	
	void updateInStream(InStreamEntity inStreamEntity);

	void updateInStreamDatas(ChartDataEntity chartDataEntity);
	
	InStreamEntity getActiveRecord(int staId);

	void createRecord(int staId);

	List<InStreamEntity> getEntityByDate(String startDate, String endDate, int staId);

	InStreamEntity getStreamByDateAndStaId(String date, int staId);
}
