package com.devsmobile.nostradamus.collector.persistence;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.domain.TrainingParameter;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.mappers.CollectionMapper;
import com.devsmobile.nostradamus.collector.persistence.mappers.TrainingParameterMapper;

@Repository
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class CollectionDAOImpl implements CollectionDAO{
	
	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private TrainingParameterMapper trainingParameterMapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(CollectionDAOImpl.class);

	@Override
	public void createCollection(Collection collection)
			throws CollectorPersistenceException {
		try{
		
			collectionMapper.insertCollection(collection);
		
		} catch(Exception e){
			LOG.error("Error inserting collection into the database",e);
			throw new CollectorPersistenceException("Impossible to insert collection into the database",e);
		}
		
		try{
			
			int idx = 0;
			for(TrainingParameter parameter : collection.getParameters()){
				parameter.setId(idx);
				trainingParameterMapper.insertParameter(collection.getId(), parameter);
				idx++;
			}
			
		} catch(Exception e){
			LOG.error("Error inserting parameter into the database",e);
			throw new CollectorPersistenceException("Impossible to insert parameter into the database",e);
		}
		
	}

}
