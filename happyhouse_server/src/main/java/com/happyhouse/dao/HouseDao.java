package com.happyhouse.dao;

import java.util.List;

import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

public interface HouseDao {

	List<HouseInfo> searchByApt(String keyword);
	List<HouseInfo> searchByDong(String keyword);
	
	List<HouseDeal> searchDeal(HouseDeal deal);
}
