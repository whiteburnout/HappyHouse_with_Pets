package com.happyhouse.mapper;

import java.util.List;

import com.happyhouse.vo.Hospital;
import com.happyhouse.vo.Park;
import com.happyhouse.vo.Pharmacy;

public interface AnimalMapper {

	List<Hospital> searchHos(String dong);
	List<Pharmacy> searchPha(String dong);
	List<Park> searchPark(String dong);
}
