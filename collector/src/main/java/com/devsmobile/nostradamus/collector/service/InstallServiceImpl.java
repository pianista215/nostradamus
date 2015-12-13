package com.devsmobile.nostradamus.collector.service;

import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.InstallDAO;
import com.devsmobile.nostradamus.collector.persistence.TestDAO;

@Service
@MapperScan("com.devsmobile.nostradamus.collector.persistence, com.devsmobile.nostradamus.collector.utils")
public class InstallServiceImpl implements InstallService{

	@Autowired
	private TestDAO testDAO;
	
	@Autowired
	private InstallDAO installDAO;

	@Override
	public void testDB() throws CollectorPersistenceException{
		testDAO.testConnection();
		Random random = new Random();
		long tableId = random.nextLong();
		if(tableId<0){
			tableId = tableId*-1;
		}
		testDAO.testCreation(tableId);
		testDAO.testInsert(tableId);
		testDAO.testDrop(tableId);
	}

	@Override
	public void installCollectorSchemas() throws CollectorPersistenceException {
		// TODO Auto-generated method stub
		
	}

}
