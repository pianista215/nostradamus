package com.devsmobile.nostradamus.collector.persistence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.mappers.EnvironmentInfoMapper;
import com.devsmobile.nostradamus.collector.persistence.mappers.SchemasMapper;

@Repository
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class InstallationDAOImpl implements InstallationDAO{
	
	@Autowired
	private EnvironmentInfoMapper enviromentInfoMapper;
	
	@Autowired
	private SchemasMapper schemasMapper;

	@Override
	public String getVersion() throws CollectorPersistenceException{
		
		try{
			return enviromentInfoMapper.getVersion();
		} catch(Exception e){
			throw new CollectorPersistenceException("Unable to obtain version", e);
		}
		
	}

	@Override
	public void installSchemaEnvironmentInformation()
			throws CollectorPersistenceException {
		
		try{
			schemasMapper.createEnvironmentInformation();
		} catch(Exception e){
			throw new CollectorPersistenceException("Unable to create Enviroment information table", e);
		}
		
	}

	@Override
	public void installSchemaCollection() throws CollectorPersistenceException {
		
		try{
			schemasMapper.createSchemaCollection();
		} catch(Exception e){
			throw new CollectorPersistenceException("Unable to create Collection table", e);
		}
		
	}

	@Override
	public void installSchemaParameterType()
			throws CollectorPersistenceException {
		
		try{
			schemasMapper.createSchemaParameterType();
			//TODO: Add types
		} catch(Exception e){
			throw new CollectorPersistenceException("Unable to create Parameter Type table", e);
		}
		
	}

	@Override
	public void installSchemaTrainingParameters()
			throws CollectorPersistenceException {
		
		try{
			schemasMapper.createSchemaTrainingParameters();
		} catch(Exception e){
			throw new CollectorPersistenceException("Unable to create Training Parameters table", e);
		}
		
	}

}
