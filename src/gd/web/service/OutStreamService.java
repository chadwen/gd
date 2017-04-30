package gd.web.service;

import java.util.List;

import gd.web.entity.ChartDataEntity;
import gd.web.entity.OutStreamEntity;

public interface OutStreamService {

	void addOutStream(OutStreamEntity outStreamEntity);

	void inactivate(int staId);
	
	void updateOutStream(OutStreamEntity outStreamEntity);

	void updateOutStreamDatas(ChartDataEntity chartDataEntity);
	
	OutStreamEntity getActiveRecord(int staId);

	void createRecord(int staId);

	List<OutStreamEntity> getEntityByDate(String startDate, String endDate, int staId);

	OutStreamEntity getStreamByDateAndStaId(String date, int staId);
}
