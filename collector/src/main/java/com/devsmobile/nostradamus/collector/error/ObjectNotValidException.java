package com.devsmobile.nostradamus.collector.error;

public class ObjectNotValidException extends CollectorException{

	private static final long serialVersionUID = 6764807177952984718L;

	public ObjectNotValidException(String msg){
		super(msg);
	}
	
	public ObjectNotValidException(String msg, Throwable e){
		super(msg,e);
	}
	
}
