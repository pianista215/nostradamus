package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Insert;

import com.devsmobile.nostradamus.collector.domain.Collection;

public interface CollectionMapper {
	
	/**
	 * Insert a collection into the database
	 * @param collection
	 */
	@Insert("INSERT into COLLECTION(name) VALUES (#{collection.name})")
	public void insertCollection(Collection collection);
}
