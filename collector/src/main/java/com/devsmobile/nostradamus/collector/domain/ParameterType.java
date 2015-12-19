package com.devsmobile.nostradamus.collector.domain;

import java.util.Date;

/**
 * Types of parameter supported by Nostradamus
 * @author Pianista
 *
 */
public enum ParameterType {
	
	//In the future may be we can have more type of integers and doubles, or allow to set the precission...
	INTEGER(1, "Integer", "A number without fractional (limit 2147483647). Ex: 1, 30, -5, 8292", Long.class, "INT"),
	REAL(2, "Real", "A real number, can be fractional (limit 999999999.9999999999) Ex: 1.243, -3.51, 6, 783.23", Double.class, "DECIMAL(20,10)"),
	CHARACTERS(3, "Characters", "A set of characters. (limit 140 characters) Ex: John, Pep, Madrid, jas127aksld", String.class, "VARCHAR(140)"),
	DATE(4, "Date", "A valid date. Ex: 1988/08/11 15:45:00, 2015/12/12, 2020/10/01, 1970/12/12", Date.class, "DATETIME");
	
	private final int id;
	private final String name;
	private final String description;
	private final Class parser;
	private final String mysqlType;
	
	ParameterType(int id, String name, String description, Class parser, String mysqlType) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parser = parser;
		this.mysqlType = mysqlType;
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

	public Class getParser() {
		return parser;
	}

	public String getMysqlType() {
		return mysqlType;
	}
	
}
