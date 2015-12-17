package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface GenericMapper {

	/**
	 * Execute a sql.
	 * Used to create table with dinamically name and attributes
	 * @param sql
	 */
	@Insert("${sql}")
	public void executeSql(@Param("sql")String sql);
}
