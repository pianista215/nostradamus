package com.devsmobile.nostradamus.collector.persistence;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.mappers.TestMapper;

@Repository
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class TestDAOImpl implements TestDAO{
	
	@Autowired
	private TestMapper testMapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(TestDAOImpl.class);

	@Override
	public void testConnection()  throws CollectorPersistenceException{
		try{
			testMapper.testConnection();
		} catch (Exception e){
			LOG.error("Error testing connection",e);
			throw new CollectorPersistenceException("Failed to connect database",e);
		}
	}

	@Override
	public void testCreation(long tableId)  throws CollectorPersistenceException{
		try{
			testMapper.testCreation(tableId);
		} catch (Exception e){
			LOG.error("Error testing creation",e);
			throw new CollectorPersistenceException("Failed to create table into database",e);
		}
	}

	@Override
	public void testInsert(long tableId)  throws CollectorPersistenceException{
		try{
			testMapper.testInsert(tableId);
		} catch (Exception e){
			LOG.error("Error testing insertion",e);
			throw new CollectorPersistenceException("Failed to insert into database",e);
		}
	}

	@Override
	public void testDrop(long tableId)  throws CollectorPersistenceException{
		try{
			testMapper.testDrop(tableId);
		} catch (Exception e){
			LOG.error("Error testing drop",e);
			throw new CollectorPersistenceException("Failed to drop table from database",e);
		}
	}

}
