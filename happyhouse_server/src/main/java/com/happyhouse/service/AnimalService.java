package com.happyhouse.service;

import java.util.List;

import com.happyhouse.vo.Hospital;
import com.happyhouse.vo.Park;
import com.happyhouse.vo.Pharmacy;

public interface AnimalService {

	List<Hospital> searchHos(String dong);
	List<Pharmacy> searchPha(String dong);
	List<Park> searchPark(String dong);
}
