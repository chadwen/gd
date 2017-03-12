package gd.web.service;

import gd.web.entity.InStreamEntity;

public interface InStreamService {

	void addInStream(InStreamEntity inStreamEntity);
	
	void inactivate();
	
	void updateInStream(InStreamEntity inStreamEntity);

	void updateInStreamDatas(int staId, String datas);
	
	InStreamEntity getActiveRecord();

	void createRecord(int staId);
}
