package com.devsmobile.nostradamus.collector.domain;

import java.text.DateFormat;

import com.devsmobile.nostradamus.collector.error.ObjectNotValidException;

public class TrainingParameter {

	private Integer id;
	private String name;
	private String description;
	private ParameterType type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ParameterType getType() {
		return type;
	}
	public void setType(ParameterType type) {
		this.type = type;
	}
	
	/**
	 * Retrieve the key used for database columns, and to receive objects
	 * @return
	 */
	public String getDatabaseColumn(){
		return "_"+id;
	}
	
	/**
	 * Try to parse the object value
	 * @param p
	 * @return
	 * @throws ObjectNotValidException
	 */
	public void castObject(Object p) throws ObjectNotValidException{
		try{
			if(ParameterType.CHARACTERS.equals(p)){
				
				String.valueOf(p);
				
			} else if(ParameterType.INTEGER.equals(p)){
				
				Integer.parseInt((String) p);
				
			} else if(ParameterType.REAL.equals(p)){
				
				Double.parseDouble((String) p);
				
			} else if(ParameterType.DATE.equals(p)){
				//TODO
				
			}  
		} catch(Exception e){
			throw new ObjectNotValidException("Impossible to convert to type:"+type.getName()+", object:"+p);
		}
		
		/*try{
			type.getClass().cast(p);
		} catch(ClassCastException e){
			throw new ObjectNotValidException("Impossible to convert to type:"+type.getName()+", object:"+p);
		}*/
	}
	
	
}
