package com.devsmobile.nostradamus.collector.service;

import java.util.List;
import java.util.Map;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.CollectionNotExistsException;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.error.ObjectNotValidException;

public interface CollectionService {

	/**
	 * Create a new collection into the database
	 * @param c
	 * @throws CollectorPersistenceException
	 */
	public void createCollection(Collection c) throws CollectorPersistenceException;
	
	/**
	 * Add the objects to the collection
	 * @param collectionId
	 * @param objects
	 * @throws CollectorPersistenceException
	 * @throws CollectionNotExistsException
	 * @throws ObjectNotValidException
	 */
	public void addObjects(String collectionId, List<Map<String,Object>> objects) throws CollectorPersistenceException,CollectionNotExistsException, ObjectNotValidException;
}
