package com.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.ReviewMapper;
import com.happyhouse.vo.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	ReviewMapper mapper;
	
	@Override
	public List<Review> searchReview(String no) {
		return mapper.selectAll(no);
	}

	@Override
	public int RegisterReview(Review review) {
		return mapper.insert(review);
	}

	@Override
	public int ModifyReview(Review review) {
		return mapper.update(review);
	}

	@Override
	public int DeleteReview(String no) {
		return mapper.delete(no);
	}
	
	
}
