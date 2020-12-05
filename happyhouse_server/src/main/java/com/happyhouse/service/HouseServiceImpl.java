package com.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.HouseDao;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDao Dao;
	
	@Override
	public List<HouseInfo> searchByApt(String keyword) {
		return Dao.searchByApt(keyword);
	}

	@Override
	public List<HouseInfo> searchByDong(String keyword) {
		return Dao.searchByDong(keyword);
	}
	
	@Override
	public List<HouseDeal> searchDeal(HouseDeal deal) {
		return Dao.searchDeal(deal);
	}

	
}
