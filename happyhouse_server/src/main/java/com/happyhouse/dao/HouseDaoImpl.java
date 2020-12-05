package com.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.HouseMapper;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

@Repository
public class HouseDaoImpl implements HouseDao {

	@Autowired
	HouseMapper mapper;
	
	@Override
	public List<HouseInfo> searchByApt(String keyword) {
		return mapper.searchByApt(keyword);
	}

	@Override
	public List<HouseInfo> searchByDong(String keyword) {
		return mapper.searchByDong(keyword);
	}

	@Override
	public List<HouseDeal> searchDeal(HouseDeal deal) {
		return mapper.searchDeal(deal);
	}
}
