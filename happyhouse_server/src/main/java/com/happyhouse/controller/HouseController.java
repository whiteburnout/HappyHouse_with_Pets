package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.HouseService;
import com.happyhouse.vo.DongCode;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

@RestController
@CrossOrigin
public class HouseController {
	
	@Autowired
	HouseService service;
	
	//houseinfo 검색
	@GetMapping(value = "/house/{keyword}")
	List<HouseInfo> searchHouseInfo(@PathVariable String keyword)
	{
		return service.searchHouseInfo("%"+keyword+"%");
	}
	
	//거래내역 검색
	@GetMapping(value = "/house/{dong}/{aptName}")
	List<HouseDeal> searchDeal(@PathVariable String dong, @PathVariable String aptName)
	{
		HouseDeal deal = new HouseDeal();
		deal.setDong(dong);
		deal.setAptName(aptName);
		return service.searchDeal(deal);
	}
	
	//자동완성 지역정보
	@GetMapping(value = "/house/auto/dong/{keyword}")
	List<DongCode> getDong(@PathVariable String keyword) {
		return service.getDong("%"+keyword+"%");
	}
	//자동완성 주택정보
	@GetMapping(value = "/house/auto/house/{keyword}")
	List<HouseInfo> getHouse(@PathVariable String keyword){
		return service.getHouse("%"+keyword+"%");
	}
	
}
