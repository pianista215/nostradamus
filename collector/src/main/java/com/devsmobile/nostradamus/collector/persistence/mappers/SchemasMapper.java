package com.devsmobile.nostradamus.collector.persistence.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface SchemasMapper {

	@Insert("CREATE TABLE ENVIRONMENT_INFORMATION("
			+ "version VARCHAR(10)"
			+ ")")
	public void createEnvironmentInformation();
	
	@Delete("DELETE FROM ENVIRONMENT_INFORMATION")
	public void deleteEnvironmentInformation();
	
	@Insert("INSERT INTO ENVIRONMENT_INFORMATION(version) VALUES (#{version})")
	public void insertVersion(String version);
	
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
	
	@Insert("INSERT INTO PARAMETER_TYPE(id,name,description) VALUES (#{id},#{name},#{description})")
	public void insertParameterType(@Param("id")Integer id, @Param("name")String name, @Param("description")String description);
	
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
