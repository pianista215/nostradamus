package com.devsmobile.nostradamus.collector.error;

/**
 * Parent of all the exceptions of the provider
 * @author Pianista
 *
 */
public class CollectorException extends Exception{

	private static final long serialVersionUID = 8079400310439015360L;
	
	public CollectorException(String msg){
		super(msg);
	}
	
	public CollectorException(String msg, Throwable e){
		super(msg,e);
	}

}
