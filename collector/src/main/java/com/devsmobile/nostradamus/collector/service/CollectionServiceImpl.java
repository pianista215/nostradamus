package com.devsmobile.nostradamus.collector.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.domain.TrainingParameter;
import com.devsmobile.nostradamus.collector.error.CollectionNotExistsException;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.error.ObjectNotValidException;
import com.devsmobile.nostradamus.collector.persistence.CollectionDAO;

@Service
@ComponentScan("com.devsmobile.nostradamus.collector.persistence")
public class CollectionServiceImpl implements CollectionService{
	
	@Autowired
	private CollectionDAO collectionDAO;

	@Override
	//TODO: Check how to work@Transactional(readOnly=false, rollbackFor = Exception.class )
	public void createCollection(Collection c)
			throws CollectorPersistenceException {
		
		collectionDAO.createCollection(c);
	}

	@Override
	//TODO: Check how to work@Transactional(readOnly=false, rollbackFor = Exception.class )
	public void addObjects(String collectionId,
			List<Map<String, Object>> objects) throws CollectorPersistenceException, CollectionNotExistsException, ObjectNotValidException {
		
		if(objects==null){
			throw new ObjectNotValidException("No objects received to insert");
		}
		
		Collection collection = collectionDAO.retrieveCollection(collectionId);
		if(collection==null){
			throw new CollectionNotExistsException("Collection not found in the database");
		}
		
		validateObjects(collection, objects);
		
		//TODO: Pagination in insert to prevent max size query
		collectionDAO.insertObjects(collection, objects);
				
	}
	
	/**
	 * Validate the objects before insert into the database
	 * @param collection
	 * @param objects
	 * @throws ObjectNotValidException
	 */
	private void validateObjects(Collection collection, List<Map<String, Object>> objects) throws ObjectNotValidException{
		for(Map<String,Object> object : objects){
			for(TrainingParameter parameter : collection.getParameters()){
				if(object.containsKey(parameter.getDatabaseColumn())){
					//try to cast
					parameter.castObject(object.get(parameter.getDatabaseColumn()));
				}
			}
		}
	}

	
}
