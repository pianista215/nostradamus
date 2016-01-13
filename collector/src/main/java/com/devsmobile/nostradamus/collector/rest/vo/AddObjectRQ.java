package com.devsmobile.nostradamus.collector.rest.vo;

import java.util.List;
import java.util.Map;

public class AddObjectRQ {

	private String collectionId;
	private List<Map<String, Object>> objects;
	
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public List<Map<String, Object>> getObjects() {
		return objects;
	}
	public void setObjects(List<Map<String, Object>> objects) {
		this.objects = objects;
	}
	
}
