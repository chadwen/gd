package gd.web.domain;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.InStreamEntity;
public interface InStreamDAO {
	@Transactional
	void addInStream(InStreamEntity inStreamEntity);

	@Transactional
	void inactivate(int staId);
	
	@Transactional
	void updateInStream (InStreamEntity inStreamEntity);

	@Transactional
	InStreamEntity getActiveRecord(int staId);
}
