package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Select;

public interface EnvironmentInfoMapper {

	@Select("SELECT version from ENVIRONMENT_INFORMATION")
	public String getVersion();
}
