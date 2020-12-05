package com.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.AnimalMapper;
import com.happyhouse.vo.Hospital;
import com.happyhouse.vo.Park;
import com.happyhouse.vo.Pharmacy;

@Repository
public class AnimalDaoImpl implements AnimalDao {
	
	@Autowired
	AnimalMapper mapper;

	@Override
	public List<Hospital> searchHos(String dong) {
		return mapper.searchHos(dong);
	}

	@Override
	public List<Pharmacy> searchPha(String dong) {
		return mapper.searchPha(dong);
	}

	@Override
	public List<Park> searchPark(String dong) {
		return mapper.searchPark(dong);
	}

	
}
