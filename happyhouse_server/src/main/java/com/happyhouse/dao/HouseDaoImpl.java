package com.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.HouseMapper;
import com.happyhouse.vo.DongCode;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

@Repository
public class HouseDaoImpl implements HouseDao {

	@Autowired
	HouseMapper mapper;
	
	@Override
	public List<HouseInfo> searchHouseInfo(String keyword) {
		return mapper.searchHouseInfo(keyword);
	}

	@Override
	public List<HouseDeal> searchDeal(HouseDeal deal) {
		return mapper.searchDeal(deal);
	}
	
	@Override
	public List<DongCode> getDong(String keyword) {
		return mapper.getDong(keyword);
	}
	
	@Override
	public List<HouseInfo> getHouse(String keyword) {
		return mapper.getHouse(keyword);
	}
}
