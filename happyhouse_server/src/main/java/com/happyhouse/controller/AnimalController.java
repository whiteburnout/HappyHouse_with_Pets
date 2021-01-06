package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.AnimalService;
import com.happyhouse.vo.Hospital;
import com.happyhouse.vo.Park;
import com.happyhouse.vo.Pharmacy;

@RestController
@CrossOrigin
public class AnimalController {

	
	@Autowired
	AnimalService service;
	//동물병원 정보 검색
	@GetMapping(value = "/animal/hospital/{dong}")
	List<Hospital> searchHos(@PathVariable String dong)
	{
		return service.searchHos("%"+dong+"%");
	}
	//동물약국 정보 검색
	@GetMapping(value = "/animal/pharmacy/{dong}")
	List<Pharmacy> searchPha(@PathVariable String dong)
	{
		return service.searchPha("%"+dong+"%");
	}
	//공원 정보 검색
	@GetMapping(value = "/animal/park/{dong}")
	List<Park> searchPark(@PathVariable String dong)
	{
		dong.substring(0, dong.length() - 1);
		return service.searchPark("%"+dong+"%");
	}
}
