package com.devsmobile.nostradamus.collector.persistence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class InstallDAOImpl implements InstallDAO{

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
