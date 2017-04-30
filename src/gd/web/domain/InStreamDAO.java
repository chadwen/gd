package gd.web.domain;

import java.util.List;

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

	@Transactional
	List<InStreamEntity> getEntityByDate(String startDate, String endDate, int staId);

	@Transactional
	InStreamEntity getStreamByDateAndStaId(String date, int staId);
}
