package com.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.HouseDao;
import com.happyhouse.vo.DongCode;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDao dao;
	
	@Override
	public List<HouseInfo> searchHouseInfo(String keyword) {
		return dao.searchHouseInfo(keyword);
	}
	
	@Override
	public List<HouseDeal> searchDeal(HouseDeal deal) {
		return dao.searchDeal(deal);
	}
	
	@Override
	public List<DongCode> getDong(String keyword) {
		return dao.getDong(keyword);
	}
	
	@Override
	public List<HouseInfo> getHouse(String keyword) {
		return dao.getHouse(keyword);
	}
	
}
