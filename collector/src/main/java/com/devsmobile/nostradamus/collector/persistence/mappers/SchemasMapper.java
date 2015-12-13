package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Insert;

public interface SchemasMapper {

	@Insert("CREATE TABLE ENVIROMENT_INFORMATION("
			+ "version VARCHAR(10)"
			+ ")")
	public void createEnvironmentInformation();
	
	@Insert("CREATE TABLE COLLECTION("
			+ "id INT AUTO_INCREMENT PRIMARY KEY,"
			+ "name VARCHAR(25),"
			+ "UNIQUE(name)"
			+ ")")
	public void createSchemaCollection();
	
	
	@Insert("CREATE TABLE PARAMETER_TYPE("
			+ "id SMALLINT PRIMARY KEY,"
			+ "name VARCHAR(25),"
			+ "description VARCHAR(140)"
			+ ")")
	public void createSchemaParameterType();
	
	//public void insertParameterType(Integer id, String name, String description);
	
	@Insert("CREATE TABLE TRAINING_PARAMETERS("
			+ "id SMALLINT,"
			+ "collection_id INT REFERENCES COLLECTION(id),"
			+ "name VARCHAR(25),"
			+ "description VARCHAR(140),"
			+ "type_id SMALLINT REFERENCES PARAMETER_TYPE(id),"
			+ "PRIMARY KEY(collection_id, id)"
			+ ")")
	public void createSchemaTrainingParameters();
	
}
