package com.devsmobile.nostradamus.collector.error;

public class CollectionNotExistsException extends CollectorException{

	private static final long serialVersionUID = -7893648689404636520L;

	public CollectionNotExistsException(String msg){
		super(msg);
	}
	
	public CollectionNotExistsException(String msg, Throwable e){
		super(msg,e);
	}
}
