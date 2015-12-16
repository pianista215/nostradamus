package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.devsmobile.nostradamus.collector.domain.Collection;

public interface CollectionMapper {
	
	/**
	 * Insert a collection into the database
	 * @param collection
	 */
	@Insert("INSERT into COLLECTION(name) VALUES (#{collection.name})")
	@Options(useGeneratedKeys = true, keyProperty = "collection.id", keyColumn = "id")
	public void insertCollection(@Param("collection")Collection collection);
}
