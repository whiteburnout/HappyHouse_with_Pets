package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.HouseService;
import com.happyhouse.vo.HouseDeal;
import com.happyhouse.vo.HouseInfo;

@RestController
@CrossOrigin
public class HouseController {
	
	@Autowired
	HouseService service;
	
	@GetMapping(value = "/house/apt/{keyword}")
	List<HouseInfo> searchByApt(@PathVariable String keyword)
	{
		return service.searchByApt("%"+keyword+"%");
	}
	
	@GetMapping(value = "/house/dong/{keyword}")
	List<HouseInfo> searchByDong(@PathVariable String keyword)
	{
		return service.searchByDong("%"+keyword+"%");
	}
	
	@GetMapping(value = "/house/{dong}/{aptName}")
	List<HouseDeal> searchDeal(@PathVariable String dong, @PathVariable String aptName)
	{
		HouseDeal deal = new HouseDeal();
		deal.setDong(dong);
		deal.setAptName(aptName);
		return service.searchDeal(deal);
	}
}
