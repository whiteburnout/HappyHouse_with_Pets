package com.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.AnimalDao;
import com.happyhouse.vo.Hospital;
import com.happyhouse.vo.Park;
import com.happyhouse.vo.Pharmacy;

@Service
public class AnimalServiceImpl implements AnimalService {
	
	@Autowired
	AnimalDao Dao;

	@Override
	public List<Hospital> searchHos(String dong) {
		return Dao.searchHos(dong);
	}

	@Override
	public List<Pharmacy> searchPha(String dong) {
		return Dao.searchPha(dong);
	}

	@Override
	public List<Park> searchPark(String dong) {
		return Dao.searchPark(dong);
	}

	
}
