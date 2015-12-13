package com.devsmobile.nostradamus.collector.domain;

/**
 * Types of parameter supported by Nostradamus
 * @author Pianista
 *
 */
public enum ParameterType {
	
	INTEGER(1, "Integer", "A number without fractional. Ex: 1, 30, -5, 8292"),
	REAL(2, "Real", "A real number, can be fractional. Ex: 1.243, -3.51, 6, 783.23"),
	CHARACTERS(3, "Characters", "A set of characters. Ex: John, Pep, Madrid, jas127aksld"),
	DATE(4, "Date", "A valid date. Ex: 1988/08/11 15:45:00, 2015/12/12, 2020/10/01, 1970/12/12");
	
	private final int id;
	private final String name;
	private final String description;
	
	ParameterType(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
}
