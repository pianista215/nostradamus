package com.devsmobile.nostradamus.collector.domain;

import java.util.List;
import java.util.StringJoiner;

import com.devsmobile.nostradamus.collector.utils.Constants;

public class Collection {

	private String id;
	private String name;
	private List<TrainingParameter> parameters;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TrainingParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<TrainingParameter> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Create the schema to store the data retrieved through the REST service
	 * @return
	 */
	public String generateCreationSQL(){
		StringBuilder sb = new StringBuilder("CREATE TABLE "+ Constants.TABLE_PREFIX+id).append("(");
		for(TrainingParameter parameter : parameters){
			sb.append("_").append(parameter.getId()).append(" ").append(parameter.getType().getMysqlType()).append(",");
		}
		//TODO: In the future support primary key or object restrictions
		//TODO: By default NULL is allowed, but we should allow that to calculate sums, etc?? Think about it
		sb.setLength(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
	
}
