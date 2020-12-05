package com.happyhouse.mapper;

import java.util.List;

import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

public interface HouseMapper {

	List<HouseInfo> searchByApt(String keyword);
	List<HouseInfo> searchByDong(String keyword);
	
	List<HouseDeal> searchDeal(HouseDeal deal);
}
