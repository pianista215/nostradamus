package com.devsmobile.nostradamus.collector.persistence;

import java.util.List;
import java.util.Map;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;

public interface CollectionDAO {

	/**
	 * Create a new collection into the database
	 * @param collection
	 * @throws CollectorPersistenceException
	 */
	public void createCollection(Collection collection) throws CollectorPersistenceException;
	
	/**
	 * Retrieve the collection from the database
	 * @param collectionId
	 * @return
	 * @throws CollectorPersistenceException
	 */
	public Collection retrieveCollection(String collectionId) throws CollectorPersistenceException;
	
	/**
	 * Insert the object into the collection
	 * @param collection
	 * @param objects
	 * @throws CollectorPersistenceException
	 */
	public void insertObjects(Collection collection, List<Map<String,Object>> objects) throws CollectorPersistenceException;
}
