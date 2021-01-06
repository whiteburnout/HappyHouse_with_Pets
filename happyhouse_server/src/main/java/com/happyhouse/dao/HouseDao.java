package com.happyhouse.dao;

import java.util.List;

import com.happyhouse.vo.DongCode;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

public interface HouseDao {

	List<HouseInfo> searchHouseInfo(String keyword);
	List<HouseDeal> searchDeal(HouseDeal deal);
	List<DongCode> getDong(String keyword);
	List<HouseInfo> getHouse(String keyword);
}
