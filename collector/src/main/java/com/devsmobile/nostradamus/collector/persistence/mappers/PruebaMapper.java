package com.devsmobile.nostradamus.collector.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.devsmobile.nostradamus.collector.domain.Prueba;

public interface PruebaMapper {

	@Select("SELECT * FROM prueba")
	List<Prueba> findAll();
}
