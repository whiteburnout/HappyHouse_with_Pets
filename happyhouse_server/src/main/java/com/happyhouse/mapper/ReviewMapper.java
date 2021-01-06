package com.happyhouse.mapper;

import java.util.List;

import com.happyhouse.vo.Review;

public interface ReviewMapper {

	public List<Review> selectAll(String no);
	public int insert(Review review);
	public int update(Review review);
	public int delete(String no);
}
