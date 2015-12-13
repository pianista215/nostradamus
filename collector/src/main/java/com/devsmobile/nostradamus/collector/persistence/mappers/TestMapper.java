package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {

	@Select("SELECT 1 from dual")
	public long testConnection();
	
	@Insert("CREATE TABLE test_collector_#{testId} (test int)")
	public void testCreation(long testId);
	
	@Insert("INSERT INTO test_collector_#{testId} VALUES (1)")
	public void testInsert(long testId);
	
	@Insert("DROP TABLE test_collector_#{testId}")
	public void testDrop(long testId);

}
