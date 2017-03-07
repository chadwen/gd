package gd.web.domain;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.OutStreamEntity;

public interface OutStreamDAO {
	@Transactional
	void addOutStream(OutStreamEntity outStreamEntity);
}
