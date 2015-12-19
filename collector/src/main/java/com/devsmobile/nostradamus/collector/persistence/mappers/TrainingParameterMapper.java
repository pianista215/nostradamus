package com.devsmobile.nostradamus.collector.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.devsmobile.nostradamus.collector.domain.TrainingParameter;

public interface TrainingParameterMapper {

	/**
	 * Insert a training parameter into the database
	 * @param collectionId
	 * @param parameter
	 */
	@Insert("INSERT INTO training_parameters(collection_id, id, name, description, type_id) "
			+ "VALUES (#{collectionId}, #{parameter.id}, #{parameter.name}, #{parameter.description}, #{parameter.type.id})")
	public void insertParameter(@Param("collectionId")String collectionId, @Param("parameter")TrainingParameter parameter);
	
	@Select("SELECT p.id as id, p.name as name, p.description as description, UPPER(t.name) as type "
			+ "FROM training_parameters p "
			+ "LEFT JOIN parameter_type t ON t.id = p.type_id "
			+ "WHERE collection_id=#{collectionId} ORDER BY p.id")
	public List<TrainingParameter> getParameters(@Param("collectionId")String collectionId);
}
