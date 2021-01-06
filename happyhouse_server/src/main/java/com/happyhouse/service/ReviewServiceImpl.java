package com.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.ReviewDao;
import com.happyhouse.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao dao;
	
	@Override
	public List<Review> searchReview(String no) {
		return dao.searchReview(no);
	}

	@Override
	public int RegisterReview(Review review) {
		return dao.RegisterReview(review);
	}

	@Override
	public int ModifyReview(Review review) {
		return dao.ModifyReview(review);
	}

	@Override
	public int DeleteReview(String no) {
		return dao.DeleteReview(no);
	}
	
	
}
