package gd.web.service;

import gd.web.entity.OutStreamEntity;

public interface OutStreamService {

	void addOutStream(OutStreamEntity outStreamEntity);

	void inactivate();
	
	void updateOutStream(OutStreamEntity outStreamEntity);

	void updateOutStreamDatas(int staId, String datas);
	
	OutStreamEntity getActiveRecord();

	void createRecord(int staId);
}
