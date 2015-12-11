package com.devsmobile.nostradamus.collector.rest.vo;

/**
 * Response from operations of Collector
 * @author Pianista
 *
 */
public class Response {

	private ResponseStatus status;
	private String msg;
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
