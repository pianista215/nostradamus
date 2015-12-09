package com.devsmobile.nostradamus.collector.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsmobile.nostradamus.collector.domain.Prueba;
import com.devsmobile.nostradamus.collector.persistence.mappers.PruebaMapper;

@Service
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class InstallServiceImpl implements InstallService{

	@Autowired
	private PruebaMapper pruebaMapper;

	@Override
	public List<Prueba> test() {
		return pruebaMapper.findAll();
	}
	
	
}
