package com.devsmobile.nostradamus.collector.error;

public class AlreadyInstalledSchemaException extends CollectorException{

	private static final long serialVersionUID = -3478164796239457487L;

	public AlreadyInstalledSchemaException(String msg){
		super(msg);
	}
	
	public AlreadyInstalledSchemaException(String msg, Throwable e){
		super(msg,e);
	}
}
