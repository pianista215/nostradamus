package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.devsmobile.nostradamus.collector.domain.TrainingParameter;

public interface TrainingParameterMapper {

	/**
	 * Insert a training parameter into the database
	 * @param collectionId
	 * @param parameter
	 */
	@Insert("INSERT INTO training_parameters(collection_id, name, description, type_id) "
			+ "VALUES (#{collectionId}, #{parameter.id}, #{parameter.name}, #{parameter.description}, #{paramater.type.id})")
	public void insertParameter(@Param("collectionId")String collectionId, @Param("parameter")TrainingParameter parameter);
}
