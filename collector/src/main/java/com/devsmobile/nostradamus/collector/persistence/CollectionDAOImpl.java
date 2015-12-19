package com.devsmobile.nostradamus.collector.persistence;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.domain.ParameterType;
import com.devsmobile.nostradamus.collector.domain.TrainingParameter;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.mappers.CollectionMapper;
import com.devsmobile.nostradamus.collector.persistence.mappers.GenericMapper;
import com.devsmobile.nostradamus.collector.persistence.mappers.TrainingParameterMapper;

@Repository
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class CollectionDAOImpl implements CollectionDAO{
	
	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private TrainingParameterMapper trainingParameterMapper;
	
	@Autowired
	private GenericMapper genericMapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(CollectionDAOImpl.class);

	@Override
	public void createCollection(Collection collection)
			throws CollectorPersistenceException {
		
		//Insert the collection
		try{
		
			collectionMapper.insertCollection(collection);
		
		} catch(Exception e){
			LOG.error("Error inserting collection into the database",e);
			throw new CollectorPersistenceException("Impossible to insert collection into the database",e);
		}
		
		//Create the parameters
		
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
		
		//Create the table to store the collection
		try{
			String sql = collection.generateCreationSQL();
			LOG.debug("SQL generated for collection:{}",sql);
			//TODO: Warning SQL injection
			genericMapper.executeSql(collection.generateCreationSQL());
		
		} catch(Exception e){
			LOG.error("Error inserting collection into the database",e);
			throw new CollectorPersistenceException("Impossible to insert collection into the database",e);
		}
		
	}

	@Override
	public Collection retrieveCollection(String collectionId)
			throws CollectorPersistenceException {
		try{
			//TODO: Cache
			Collection c = collectionMapper.retrieveCollection(collectionId);
			if(c!=null){
				c.setParameters(trainingParameterMapper.getParameters(collectionId));
			}
			return c;
		} catch(Exception e){
			LOG.error("Impossible to retrieve collection&parameters",e);
			throw new CollectorPersistenceException("Impossible to retrieve collection&parameters",e);
		}
	}

	@Override
	public void insertObjects(Collection collection, List<Map<String, Object>> objects)
			throws CollectorPersistenceException {
		try{
			//Warning SQL injection
			genericMapper.executeSql(generateInsertSQL(collection, objects));
		} catch(Exception e){
			LOG.error("Impossible to retrieve collection&parameters",e);
			throw new CollectorPersistenceException("Impossible to retrieve collection&parameters",e);
		}
		
	}
	
	private String generateInsertSQL(Collection collection, List<Map<String, Object>> objects){
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(collection.getTableName()).append("(");
		
		for(TrainingParameter parameter : collection.getParameters()){
			sb.append(parameter.getDatabaseColumn()).append(",");
		}
		
		sb.setLength(sb.length()-1); //Last ,
		sb.append(") VALUES ");
		
		//TODO: SQL Injection: Find a way to bind the parameters to avoid SQL injections
		
		for(Map<String,Object> object : objects){
			StringJoiner sj = new StringJoiner(",", "(", "),");
			for(TrainingParameter parameter : collection.getParameters()){
				if(ParameterType.CHARACTERS.equals(parameter.getType()) || ParameterType.DATE.equals(parameter.getType())){
					sj.add("'"+object.get(parameter.getDatabaseColumn())+"'");
				} else{
					sj.add(object.get(parameter.getDatabaseColumn()).toString());
				}
				
			}
			sb.append(sj.toString());
		}
		
		sb.setLength(sb.length()-1); //Last ,
		
		return sb.toString();
	}

}
