package com.devsmobile.nostradamus.collector.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.persistence.CollectionDAO;

@Service
@MapperScan("com.devsmobile.nostradamus.collector.persistence")
public class CollectionServiceImpl implements CollectionService{
	
	@Autowired
	private CollectionDAO collectionDAO;

	@Override
	//TODO: Check how to work@Transactional(readOnly=false, rollbackFor = Exception.class )
	public void createCollection(Collection c)
			throws CollectorPersistenceException {
		
		collectionDAO.createCollection(c);
	}

	
}
