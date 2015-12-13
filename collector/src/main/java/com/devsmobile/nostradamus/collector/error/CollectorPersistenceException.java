package com.devsmobile.nostradamus.collector.error;

public class CollectorPersistenceException extends CollectorException{

	private static final long serialVersionUID = 5997788641687017599L;
	
	public CollectorPersistenceException(String msg) {
		super(msg);
	}
	
	public CollectorPersistenceException(String msg, Throwable e) {
		super(msg,e);
	}

}
