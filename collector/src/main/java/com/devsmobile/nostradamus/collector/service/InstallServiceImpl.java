package com.devsmobile.nostradamus.collector.service;

import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsmobile.nostradamus.collector.domain.ParameterType;
import com.devsmobile.nostradamus.collector.error.AlreadyInstalledSchemaException;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.InstallationDAO;
import com.devsmobile.nostradamus.collector.persistence.TestDAO;
import com.devsmobile.nostradamus.collector.utils.Constants;

@Service
@MapperScan("com.devsmobile.nostradamus.collector.persistence")
public class InstallServiceImpl implements InstallService{

	@Autowired
	private TestDAO testDAO;
	
	@Autowired
	private InstallationDAO installationDAO;

	//TODO: Check how to work@Transactional(readOnly=false, rollbackFor = Exception.class )
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
	public void testDBConnection() throws CollectorPersistenceException {
		testDAO.testConnection();
	}

	//TODO: Check how to work@Transactional(readOnly=false, rollbackFor = Exception.class )
	@Override
	public void installCollectorSchemas() throws CollectorPersistenceException,AlreadyInstalledSchemaException {
		
		testDB();
		
		//Check if we already have created the schema, if not install the schema
		try{
			String version = installationDAO.getVersion();
			if(!version.equals(Constants.VERSION)){
				//TODO:Upgrade for next versions...
			} else {
				throw new AlreadyInstalledSchemaException("You are trying to override a existing installation. "
						+ "Please make a copy of your data if you want to backup it, and delete the database manually");
			}
		} catch(CollectorPersistenceException e){
			//No version installed
		}
		
		installationDAO.installSchemaEnvironmentInformation();
		installationDAO.setVersion(Constants.VERSION);
		
		installationDAO.installSchemaCollection();
		installationDAO.installSchemaParameterType();
		
		for(ParameterType type: ParameterType.values()){
			installationDAO.insertParameterType(type);
		}
		installationDAO.installSchemaTrainingParameters();
		
	}

}
